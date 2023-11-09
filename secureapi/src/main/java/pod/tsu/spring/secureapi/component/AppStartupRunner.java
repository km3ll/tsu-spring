package pod.tsu.spring.secureapi.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(AppStartupRunner.class);

    public AppStartupRunner() {
        logger.info("Created");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Pod started");
    }

}
