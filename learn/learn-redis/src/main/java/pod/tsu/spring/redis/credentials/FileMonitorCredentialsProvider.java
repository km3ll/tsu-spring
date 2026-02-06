package pod.tsu.spring.redis.credentials;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
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

	private WatchService watchService;

	private final Path credentialsFilePath;

	private final ExecutorService executor = Executors.newSingleThreadExecutor();

	private final AtomicReference<Optional<Credentials>> currentCredentials = new AtomicReference<>(Optional.empty());

	private Consumer<Credentials> onCredentialsLoaded;

	public FileMonitorCredentialsProvider(Path filePath) {
		if (!filePath.isAbsolute()) {
			throw new IllegalArgumentException("Credentials file path must be absolute");
		}
		if (!filePath.toFile().isFile()) {
			throw new IllegalArgumentException("Credentials file path must point to a file");
		}
		this.credentialsFilePath = filePath;
	}

	public void shutdown() throws IOException {
		if (watchService != null) {
			watchService.close();
		}

		executor.shutdown();
	}

	public void start() {
		try {
			watchService = startWatchService();
			logger.info("Load initial credentials");
			loadCredentials();
		}
		catch (IOException ioException) {
			throw new IllegalStateException("Unable to start credentials watcher", ioException);
		}
		logger.info("Start monitoring");
		executor.submit(this::credentialsChangeHandler);
	}

	public void setOnCredentialsLoaded(Consumer<Credentials> onCredentialsLoaded) {
		this.onCredentialsLoaded = onCredentialsLoaded;
	}

	private WatchService startWatchService() throws IOException {
		final Path parent = credentialsFilePath.getParent();
		logger.info("Start watch service on folder {}", parent);
		final WatchService newWatchService = FileSystems.getDefault().newWatchService();
		parent.register(newWatchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.OVERFLOW);
		return newWatchService;

	}

	private Object credentialsChangeHandler() throws InterruptedException, IOException {
		logger.info("Watching for changes in {}", credentialsFilePath);
		WatchKey key;
		while ((key = watchService.take()) != null) {
			for (WatchEvent<?> watchEvent : key.pollEvents()) {
				logger.info("watch event {} on {}", watchEvent.kind(), watchEvent.context());
				if (watchEvent.kind() != StandardWatchEventKinds.ENTRY_DELETE
						&& watchEvent.context().toString().equals(credentialsFilePath.getFileName().toString())) {
					logger.info("{} event received on watched file {}", watchEvent.kind(), watchEvent.context());
					loadCredentials();
				}
			}
			key.reset();
		}
		return null;
	}

	public Credentials getCredentials() {
		return currentCredentials.get().orElseThrow(IllegalArgumentException::new);
	}

	private void loadCredentials() throws IOException {
		final ObjectMapper objectMapper = new ObjectMapper();
		logger.info("loading credentials from file: {}", credentialsFilePath.getFileName());
		final File credentialsFile = this.credentialsFilePath.toFile();
		@SuppressWarnings("unchecked")
		final HashMap<String, String> credentialsMap = objectMapper.readValue(credentialsFile, HashMap.class);
		final Credentials credentials = new Credentials(credentialsMap.get("username"), credentialsMap.get("password"));
		if (onCredentialsLoaded != null) {
			onCredentialsLoaded.accept(credentials);
		}
		logger.info("Setting new credentials {}", credentials);
		currentCredentials.set(Optional.of(credentials));
	}

	public static FileMonitorCredentialsProvider create(String filePath) {
		final Path path = Paths.get(filePath);
		final FileMonitorCredentialsProvider fileMonitorCredentialsProvider = new FileMonitorCredentialsProvider(path);
		fileMonitorCredentialsProvider.start();
		return fileMonitorCredentialsProvider;
	}

	public static FileMonitorCredentialsProvider create(String filePath, Consumer<Credentials> onCredentialsLoaded) {
		final Path path = Paths.get(filePath);
		final FileMonitorCredentialsProvider fileMonitorCredentialsProvider = new FileMonitorCredentialsProvider(path);
		fileMonitorCredentialsProvider.setOnCredentialsLoaded(onCredentialsLoaded);
		fileMonitorCredentialsProvider.start();
		return fileMonitorCredentialsProvider;
	}

}
