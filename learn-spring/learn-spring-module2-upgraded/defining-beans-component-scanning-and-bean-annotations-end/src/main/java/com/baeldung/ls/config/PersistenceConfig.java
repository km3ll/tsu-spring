package com.baeldung.ls.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.baeldung.ls.persistence" })
public class PersistenceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceConfig.class);

    public PersistenceConfig() {
        LOGGER.info("Persistence configuration loaded");
    }

}