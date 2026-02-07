package pod.tsu.spring.files.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pod.tsu.spring.files.rest.dto.ResponseDto;

@RestController
@RequestMapping("/api/")
public class AppController {

	private static final Logger logger = LoggerFactory.getLogger(AppController.class);

	public AppController() {
	}

	@GetMapping("greeting")
	public ResponseEntity<ResponseDto> getGreeting() {
		logger.info("Running getGreeting");
		var response = new ResponseDto("Hello, pod!");
		return ResponseEntity.ok(response);
	}

}
