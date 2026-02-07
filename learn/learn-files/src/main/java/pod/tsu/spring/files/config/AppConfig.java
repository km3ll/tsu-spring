package pod.tsu.spring.files.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pod.tsu.spring.files.config.props.CredentialsProps;
import pod.tsu.spring.files.model.credentials.Credentials;
import pod.tsu.spring.files.model.credentials.FileMonitorCredentialsProvider;

@Configuration
public class AppConfig {

	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

	private int counter = 0;

	public AppConfig() {
		logger.info("Building AppConfig");
	}

	private void incrementCounter(Credentials credentials) {
		counter++;
		logger.info("Credentials changed counter: {}", counter);
	}

	@Bean
	public FileMonitorCredentialsProvider credentialsProvider(CredentialsProps properties) {
		return FileMonitorCredentialsProvider.create(properties.getFilePath(), this::incrementCounter);
	}

}
