package pod.tsu.spring.files.model.credentials;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class FileMonitorCredentialsProvider implements CredentialsProvider {

	private static final Logger logger = LoggerFactory.getLogger(FileMonitorCredentialsProvider.class);

	private final AtomicReference<Optional<Credentials>> atomicCredentials = new AtomicReference<>(Optional.empty());

	private final ObjectMapper objectMapper = new ObjectMapper();

	private final Path filePath;

	private final Consumer<Credentials> onCredentialsChanged;

	private WatchService watchService;

	private ExecutorService executor;

	public static FileMonitorCredentialsProvider create(String filePath) {
		logger.info("Creating FileMonitorCredentialsProvider without consumer for file: {}", filePath);
		return new FileMonitorCredentialsProvider(Paths.get(filePath), credentials -> {
		});
	}

	public static FileMonitorCredentialsProvider create(String filePath, Consumer<Credentials> onCredentialsChanged) {
		logger.info("Creating FileMonitorCredentialsProvider with consumer for file: {}", filePath);
		return new FileMonitorCredentialsProvider(Paths.get(filePath), onCredentialsChanged);
	}

	public Credentials getCredentials() {
		return atomicCredentials.get().orElseThrow(IllegalStateException::new);
	}

	@PostConstruct
	public void start() {
		try {
			logger.info("Starting monitor for file: {}", filePath);
			if (watchService == null) {
				watchService = startWatchService();
			}
			if (executor == null) {
				executor = Executors.newSingleThreadExecutor();
				executor.submit(credentialsChangedHandler());
			}
		}
		catch (Exception e) {
			logger.error("Could not start monitor", e);
		}
	}

	@PreDestroy
	public void stop() {
		try {
			logger.info("Stopping monitor for file: {}", filePath);
			if (watchService != null) {
				watchService.close();
			}
			if (executor != null) {
				executor.shutdown();
			}
		}
		catch (IOException e) {
			logger.error("Could not stop monitor", e);
		}
	}

	private FileMonitorCredentialsProvider(Path filePath, Consumer<Credentials> onCredentialsChanged) {
		if (!filePath.isAbsolute()) {
			throw new IllegalArgumentException("Credentials file path it not absolute");
		}
		if (!filePath.toFile().isFile()) {
			throw new IllegalArgumentException("Credentials file path does not point to a file");
		}
		this.filePath = filePath;
		this.onCredentialsChanged = (onCredentialsChanged == null) ? credentials -> {
		} : onCredentialsChanged;
	}

	private WatchService startWatchService() throws IOException {
		logger.info("Starting watch service");
		Path parent = filePath.getParent();
		WatchService newWatchService = FileSystems.getDefault().newWatchService();
		parent.register(newWatchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY, OVERFLOW);
		return newWatchService;
	}

	private Runnable credentialsChangedHandler() {
		return () -> {
			logger.info("Starting credentials changed handler");
			WatchKey key;
			try {
				while ((key = watchService.take()) != null) {
					key.pollEvents().forEach(this::processEvent);
					key.reset();
				}
			}
			catch (InterruptedException e) {
				logger.error("Credentials changed handler was interrupted", e);
				Thread.currentThread().interrupt();
			}
		};
	}

	private void processEvent(WatchEvent<?> event) {
		if (event.context().toString().equals(filePath.getFileName().toString())) {
			logger.info("Watch event {} on {}", event.kind(), event.context());
			if (event.kind() == OVERFLOW) {
				logger.warn("Watch service overflowed");
			}
			if (event.kind() == ENTRY_DELETE) {
				logger.warn("Credentials file was deleted");
			}
			if (event.kind() == ENTRY_CREATE || event.kind() == ENTRY_MODIFY) {
				reloadCredentials();
			}
		}
	}

	private void reloadCredentials() {
		logger.info("Reloading credentials");
		try {
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = objectMapper.readValue(filePath.toFile(), HashMap.class);
			Credentials credentials = Credentials.of(map.get("username"), map.get("password"));
			atomicCredentials.set(Optional.of(credentials));
			onCredentialsChanged.accept(credentials);
		}
		catch (IOException e) {
			logger.error("Could not reload credentials", e);
		}
	}

}
