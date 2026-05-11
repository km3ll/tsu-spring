package tsu.pod.sandbox.scheduling.config;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Slf4j
@EnableAsync
@Configuration
@EnableScheduling
@Profile("scheduling")
public class SchedulingConfig {

	private ThreadPoolTaskScheduler scheduler;

	@Bean
	ThreadPoolTaskScheduler taskScheduler() {
		scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(5);
		scheduler.setThreadNamePrefix("scheduled-");
		scheduler.initialize();
		log.info("ThreadPoolTaskScheduler initialized");
		return scheduler;
	}

	@PreDestroy
	public void preDestroy() {
		scheduler.shutdown();
		log.info("ThreadPoolTaskScheduler shutdown");
	}

}
