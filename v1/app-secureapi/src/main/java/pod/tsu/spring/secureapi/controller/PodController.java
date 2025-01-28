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
@RequestMapping("/pod")
public class PodController {

    private final Logger logger = LoggerFactory.getLogger(PodController.class);
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public PodController() {
        logger.info("Created");
    }

    @GetMapping("status")
    public ResponseEntity<PodResponseDto> getStatus() {
        PodResponseDto body = PodResponseDto.builder()
            .timestamp(dateTimeFormatter.format(LocalDateTime.now()))
            .message("UP!")
            .build();
        return ResponseEntity.ok(body);
    }

}