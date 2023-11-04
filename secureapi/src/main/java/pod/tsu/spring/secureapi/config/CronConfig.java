package pod.tsu.spring.secureapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableAsync
@EnableScheduling
public class CronConfig {

    private final Logger logger = LoggerFactory.getLogger(CronConfig.class);

    public CronConfig() {
        logger.info("Created");
    }

    @Scheduled(cron = "${scheduled.cron.expression}")
    public void runTaskWithCronExpression() {
        logger.info("Task: cron expression");
    }

    /**
     * The duration between the end of the last execution and the start of the next execution is fixed.
     * The task always waits until the previous one is finished.
     */
    @Scheduled(fixedDelayString = "${scheduled.sync.fixed.delay-in-millis}")
    public void runSyncTaskWithFixedDelay() {
        logger.info("Sync task: fixed delay");
    }

    @Scheduled(fixedRateString = "${scheduled.sync.fixed.rate-in-millis}")
    public void runSyncTaskWithFixedRateSync() {
        logger.info("Sync task: fixed rate");
    }

    @Async
    @Scheduled(fixedRateString = "${scheduled.async.fixed.rate-in-millis}")
    public void runAsyncTaskWithFixedRate() {
        logger.info("Async task: fixed rate");
    }

}
