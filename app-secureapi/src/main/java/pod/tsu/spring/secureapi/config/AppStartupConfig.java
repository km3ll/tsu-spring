package pod.tsu.spring.secureapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppStartupConfig {

    private final Logger logger = LoggerFactory.getLogger(AppStartupConfig.class);

    public AppStartupConfig() {
        logger.info("Created");
    }

    @Bean
    public ApplicationRunner applicationRunner(@Value("${startup.pod.id}") String message) {
        return args -> {
            logger.info("Pod {} started", message);
        };
    }

}