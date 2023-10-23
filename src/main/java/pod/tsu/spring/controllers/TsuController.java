package pod.tsu.spring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pod.tsu.spring.dto.TsuResponseDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/tsu")
public class TsuController {

    private final Logger logger = LoggerFactory.getLogger(TsuController.class);
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public TsuController() {
        logger.info("Created");
    }

    private TsuResponseDto buildResponse(String message) {
        return TsuResponseDto.builder()
            .datetime(dateTimeFormatter.format(LocalDateTime.now()))
            .message(message)
            .build();
    }

    @GetMapping("/status")
    public ResponseEntity<TsuResponseDto> getStatus() {
        return ResponseEntity.ok(buildResponse("UP!"));
    }

}