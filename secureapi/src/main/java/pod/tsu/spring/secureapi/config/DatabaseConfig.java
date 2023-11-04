package pod.tsu.spring.secureapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pod.tsu.spring.secureapi.config.prop.DatabaseProperties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class DatabaseConfig {

    private final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    private DatabaseProperties properties;

    @Autowired
    public DatabaseConfig(DatabaseProperties properties) {
        this.properties = properties;
        logger.info("Created");
    }

    @PostConstruct
    public void postConstruct() {
        logger.info("Running post-construct using properties: {}", properties.toString());
    }

    @PreDestroy
    public void preDestroy() {
        logger.info("Running pre-destroy");
    }

}