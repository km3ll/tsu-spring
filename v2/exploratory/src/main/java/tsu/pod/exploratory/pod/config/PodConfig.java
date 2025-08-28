package tsu.pod.exploratory.pod.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PodConfig {

    private final Logger logger = LoggerFactory.getLogger(PodConfig.class);

    public PodConfig() {
        logger.info("Created");
    }

    @Bean
    public ApplicationRunner applicationRunner(@Value("${app.name}") String appName) {
        return args -> {
            logger.info("{} started", appName);
        };
    }

}
