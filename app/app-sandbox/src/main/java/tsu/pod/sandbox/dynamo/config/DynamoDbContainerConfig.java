package tsu.pod.sandbox.dynamo.config;

import jakarta.annotation.PreDestroy;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
@Profile("dynamodb")
public class DynamoDbContainerConfig {

	private final Logger logger = LoggerFactory.getLogger(DynamoDbContainerConfig.class);

	private GenericContainer<?> container;

	public DynamoDbContainerConfig() {
		logger.info("Initialized");
	}

	@Bean()
	GenericContainer<?> dynamoDbContainer() {
		container = new GenericContainer<>(DockerImageName.parse("amazon/dynamodb-local:latest"))
			.withStartupTimeout(Duration.ofSeconds(60))
			.withExposedPorts(8000);

		container.start();
		logger.info("DynamoDbContainer started: {}", getEndpoint());

		return container;
	}

	@PreDestroy
	void stopContainer() {
		if (container != null) {
			container.stop();
		}
		logger.info("DynamoDbContainer stopped");
	}

	private String getEndpoint() {
		return String.format("http://%s:%d", container.getHost(), container.getMappedPort(8000));
	}

}
