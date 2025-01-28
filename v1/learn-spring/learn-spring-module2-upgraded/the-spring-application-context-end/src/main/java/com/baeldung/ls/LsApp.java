package com.baeldung.ls;

import com.baeldung.ls.service.IProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class LsApp {
    private static final Logger LOG = LoggerFactory.getLogger(LsApp.class);

    public static void main(final String... args) {

        SpringApplication.run(LsApp.class, args);

        LOG.info("> Creating a new context");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.baeldung.ls.persistence.repository");
        ctx.scan("com.baeldung.ls.service");

        LOG.info("> Context created with id {}", ctx.getId());

        IProjectService projectService = ctx.getBean("projectServiceImpl", IProjectService.class);

        LOG.info("> Finding project with ID = 1");
        LOG.info("> {}", projectService.findById(1L));

        LOG.info("> Context active before close: {}", ctx.isActive());
        ctx.close();
        LOG.info("> Context active after close: {}", ctx.isActive());

    }
}
