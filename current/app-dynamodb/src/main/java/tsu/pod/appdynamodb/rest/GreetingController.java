package tsu.pod.appdynamodb.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class GreetingController {

    private final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @GetMapping("greeting")
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("Hello Pod");
    }

}
