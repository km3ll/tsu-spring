package tsu.pod.sandbox.dynamo.config;

import jakarta.annotation.PreDestroy;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@Configuration
@Profile("dynamodb")
public class DynamoContainerConfig {

	private GenericContainer<?> container;

	public DynamoContainerConfig() {
		log.info("Initialized");
	}

	@Bean()
	GenericContainer<?> dynamoDbContainer() {
		container = new GenericContainer<>(DockerImageName.parse("amazon/dynamodb-local:latest"))
			.withStartupTimeout(Duration.ofSeconds(60))
			.withExposedPorts(8000);
		container.start();

		log.info("DynamoDbContainer started: {}", getEndpoint());
		return container;
	}

	@PreDestroy
	void stopContainer() {
		if (container != null) {
			container.stop();
		}
		log.info("DynamoDbContainer stopped");
	}

	private String getEndpoint() {
		return String.format("http://%s:%d", container.getHost(), container.getMappedPort(8000));
	}

}
