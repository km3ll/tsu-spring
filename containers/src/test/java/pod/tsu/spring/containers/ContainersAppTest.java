package pod.tsu.spring.containers;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Container")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ContainersAppTest {

    @Test
    public void smokeTest() {
        assertTrue(true);
    }

}