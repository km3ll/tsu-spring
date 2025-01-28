package pod.tsu.spring.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import pod.tsu.spring.bookstore.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
