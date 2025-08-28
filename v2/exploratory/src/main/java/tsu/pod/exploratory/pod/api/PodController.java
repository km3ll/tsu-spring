package tsu.pod.exploratory.pod.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tsu.pod.exploratory.pod.api.dto.GreetResponse;

@RestController
@RequestMapping("/v1/pod/")
public class PodController {

	@GetMapping("greeting")
	public ResponseEntity<GreetResponse> greet() {
		var response = new GreetResponse("Hello Pod");
		return ResponseEntity.ok(response);
	}

}
