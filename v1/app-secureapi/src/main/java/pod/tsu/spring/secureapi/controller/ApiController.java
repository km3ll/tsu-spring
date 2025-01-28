package pod.tsu.spring.secureapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pod.tsu.spring.secureapi.dto.PodResponseDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final Logger logger = LoggerFactory.getLogger(ApiController.class);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public ApiController() {
        logger.info("Created");
    }

    @GetMapping("status")
    public ResponseEntity<PodResponseDto> getStatus() {
        PodResponseDto response = PodResponseDto.builder()
            .message("UP!")
            .timestamp(formatter.format(LocalDateTime.now()))
            .build();
        return ResponseEntity.ok(response);
    }

}