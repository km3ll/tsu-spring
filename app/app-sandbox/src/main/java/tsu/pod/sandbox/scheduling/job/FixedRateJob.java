package tsu.pod.sandbox.scheduling.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("scheduling")
@Component
public class FixedRateJob {

	@Scheduled(fixedRate = 20000L)
	public void execute() {
		log.info("Executed (task start)");
	}

}
