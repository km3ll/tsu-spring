package pod.tsu.spring.secureapi.controllers;

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
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    public ApiController() {
        logger.info("Created");
    }
    private PodResponseDto buildResponse(String message) {
        return PodResponseDto.builder()
            .datetime(dateTimeFormatter.format(LocalDateTime.now()))
            .message(message)
            .build();
    }

    @GetMapping("status")
    public ResponseEntity<PodResponseDto> getStatus() {
        return ResponseEntity.ok(buildResponse("UP!"));
    }

}