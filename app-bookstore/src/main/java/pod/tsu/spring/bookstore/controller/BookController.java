package pod.tsu.spring.bookstore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pod.tsu.spring.bookstore.model.Book;
import pod.tsu.spring.bookstore.repository.BookRepository;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
        logger.info("Created");
    }

    @GetMapping
    public Iterable<Book> findAll() {
        return repository.findAll();
    }

}