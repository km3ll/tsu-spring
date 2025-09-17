package com.baeldung.ls.spring;

import com.baeldung.ls.persistence.repository.IProjectRepository;
import com.baeldung.ls.persistence.repository.impl.ProjectRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceConfig.class);

    public PersistenceConfig() {
        LOGGER.info("Persistence configuration loaded");
    }

    @Bean
    public IProjectRepository projectRepository() {
        LOGGER.info("Creating project repository");
        return new ProjectRepositoryImpl();
    }

}