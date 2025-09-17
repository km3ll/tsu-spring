package pod.tsu.spring.bookstore.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pod.tsu.spring.bookstore.model.Book;
import pod.tsu.spring.bookstore.repository.BookRepository;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository) {
        return args -> {
            Book book = new Book(null, "Functional Programming in Java", 999, "Venkat Subramaniam");
            repository.save(book);
        };
    }

}
