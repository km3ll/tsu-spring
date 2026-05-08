package tsu.pod.sandbox.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tsu.pod.sandbox.api.dto.StatusDto;

@Slf4j
@RestController
@RequestMapping("/api/")
public class SandboxController {

	public SandboxController() {
		log.info("Initialized");
	}

	@GetMapping("status")
	public ResponseEntity<StatusDto> getStatus() {
		return ResponseEntity.ok(new StatusDto("UP!"));
	}

}
