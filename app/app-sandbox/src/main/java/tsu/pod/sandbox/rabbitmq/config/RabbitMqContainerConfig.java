package tsu.pod.sandbox.rabbitmq.config;

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
@Profile("rabbitmq")
public class RabbitMqContainerConfig {

	private GenericContainer<?> container;

	public RabbitMqContainerConfig() {
		log.info("Initialized");
	}

	@Bean
	GenericContainer<?> rabbitMqContainer() {
		container = new GenericContainer<>(DockerImageName.parse("rabbitmq:4-management"))
			.withStartupTimeout(Duration.ofSeconds(60))
			.withExposedPorts(5672, 15672);
		container.start();

		log.info("RabbitMqContainer started AMQP: {} Admin UI: {}", getAmqpEndpoint(), getUiEndpoint());
		return container;
	}

	@PreDestroy
	void stopContainer() {
		if (container != null) {
			container.stop();
		}
		log.info("RabbitMqContainer stopped");
	}

	private String getAmqpEndpoint() {
		return String.format("http://%s:%d", container.getHost(), container.getMappedPort(5672));
	}

	private String getUiEndpoint() {
		return String.format("http://%s:%d", container.getHost(), container.getMappedPort(15672));
	}

}
