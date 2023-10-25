package pod.tsu.spring.bookstore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pod.tsu.spring.bookstore.model.Book;
import pod.tsu.spring.bookstore.repository.BookRepository;

@RestController
@RequestMapping("/pod")
public class PodController {

    private final Logger logger = LoggerFactory.getLogger(PodController.class);

    private final BookRepository repository;

    public PodController(BookRepository repository) {
        this.repository = repository;
        logger.info("Created");
    }

    @GetMapping("status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("UP!");
    }

}
