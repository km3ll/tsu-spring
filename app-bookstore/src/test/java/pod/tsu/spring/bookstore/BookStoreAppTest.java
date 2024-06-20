package pod.tsu.spring.bookstore;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Tag("Integration")
@ActiveProfiles({"dev"})
class BookStoreAppTest {

    @Test
    void contextLoads() {
    }

}