package tsu.pod.base.api;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tsu.pod.base.api.dto.GreetResponse;

@Profile("base")
@RestController
@RequestMapping("/v1/pod/")
public class BaseController {

	@GetMapping("greeting")
	public ResponseEntity<GreetResponse> greet() {
		var response = new GreetResponse("Hello Pod");
		return ResponseEntity.ok(response);
	}

}
