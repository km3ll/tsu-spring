package tsu.pod.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("base")
@Configuration
public class BaseConfig {

	private final Logger logger = LoggerFactory.getLogger(BaseConfig.class);

	public BaseConfig() {
		logger.info("Created");
	}

	@Bean
	public ApplicationRunner applicationRunner(@Value("${app.name}") String appName) {
		return args -> {
			logger.info("{} started", appName);
		};
	}

}
