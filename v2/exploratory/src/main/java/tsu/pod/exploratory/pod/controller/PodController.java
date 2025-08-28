package tsu.pod.exploratory.pod.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pod/")
public class PodController {

	private final Logger logger = LoggerFactory.getLogger(PodController.class);

	@GetMapping("greeting")
	public ResponseEntity<String> getGreeting() {
		return ResponseEntity.ok("Hello Pod");
	}

}
