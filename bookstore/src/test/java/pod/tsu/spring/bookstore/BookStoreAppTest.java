package pod.tsu.spring.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"test"})
class BookStoreAppTest {

    @Test
    void contextLoads() {
    }

}