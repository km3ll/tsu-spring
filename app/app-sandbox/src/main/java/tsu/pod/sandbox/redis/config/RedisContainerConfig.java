package tsu.pod.sandbox.redis.config;

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
@Profile("redis")
public class RedisContainerConfig {

	private GenericContainer<?> container;

	public RedisContainerConfig() {
		log.info("Initialized");
	}

	@Bean
	GenericContainer<?> redisContainer() {
		container = new GenericContainer<>(DockerImageName.parse("redis:6.2.6-alpine"))
			.withStartupTimeout(Duration.ofSeconds(60))
			.withExposedPorts(6379);
		container.start();

		log.info("RedisContainer started: {}", getEndpoint());
		return container;
	}

	@PreDestroy
	void stopContainer() {
		if (container != null) {
			container.stop();
		}
		log.info("RedisContainer stopped");
	}

	private String getEndpoint() {
		return String.format("http://%s:%d", container.getHost(), container.getMappedPort(6379));
	}

}
