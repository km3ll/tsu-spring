package tsu.pod.sandbox.postgresql.config;

import jakarta.annotation.PreDestroy;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@Configuration
@Profile("postgresql")
public class PostgresqlContainerConfig {

	private PostgreSQLContainer<?> container;

	public PostgresqlContainerConfig() {
		log.info("Initialized");
	}

	@Bean
	PostgreSQLContainer<?> postgreSQLContainer() {
		container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:17"))
			.withStartupTimeout(Duration.ofSeconds(60))
			.withDatabaseName("postgres")
			.withUsername("guest")
			.withPassword("guest")
			.withExposedPorts(5432);
		container.start();

		log.info("PostgreSQLContainer started: {}", getEndpoint());
		return container;
	}

	@PreDestroy
	void stopContainer() {
		if (container != null) {
			container.stop();
		}
		log.info("PostgreSQLContainer stopped");
	}

	private String getEndpoint() {
		return String.format("http://%s:%d", container.getHost(), container.getMappedPort(5432));
	}

}
