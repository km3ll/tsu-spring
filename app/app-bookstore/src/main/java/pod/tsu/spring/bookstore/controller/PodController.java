package pod.tsu.spring.bookstore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pod.tsu.spring.bookstore.dto.ResponseDto;
import pod.tsu.spring.bookstore.repository.BookRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/pod")
public class PodController {

    private final Logger logger = LoggerFactory.getLogger(PodController.class);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    private final BookRepository repository;

    public PodController(BookRepository repository) {
        this.repository = repository;
        logger.info("Created");
    }

    @GetMapping("status")
    public ResponseEntity<ResponseDto> getStatus() {
        ResponseDto response = ResponseDto.builder()
            .message("UP!")
            .timestamp(formatter.format(LocalDateTime.now()))
            .build();
        return ResponseEntity.ok(response);
    }

}
