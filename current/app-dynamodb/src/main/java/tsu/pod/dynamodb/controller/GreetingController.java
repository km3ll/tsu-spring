package tsu.pod.dynamodb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class GreetingController {

	@GetMapping("greeting")
	public ResponseEntity<String> getGreeting() {
		return ResponseEntity.ok("Hello Pod");
	}

}
