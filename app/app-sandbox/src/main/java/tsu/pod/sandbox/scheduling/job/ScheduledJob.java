package tsu.pod.sandbox.scheduling.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("scheduling")
@Component
public class ScheduledJob {

	@Scheduled(cron = "0 */2 * * * *")
	public void execute() {
		log.info("Scheduled (2 mins)");
	}

}
