package com.baeldung.ls.config;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NewConfig implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(NewConfig.class);

    public NewConfig() {
        super();
        LOG.info("NewConfig instantiated");
    }

    @PostConstruct
    public void init() {
        LOG.info("PostConstruct executed");
    }

    @PreDestroy
    public void destroy() {
        LOG.info("PreDestroy executed");
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Run method executed");
    }

}
