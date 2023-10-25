package pod.tsu.spring.secureapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@ActiveProfiles({"test"})
class SecureApiAppTest {

    @Test
    void contextLoads() {
    }

}