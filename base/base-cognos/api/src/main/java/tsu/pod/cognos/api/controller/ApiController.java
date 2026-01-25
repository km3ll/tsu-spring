package tsu.pod.cognos.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ApiController {

    @GetMapping
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("Hello, pod!");
    }
}
