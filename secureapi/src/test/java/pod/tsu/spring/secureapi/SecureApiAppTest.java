package pod.tsu.spring.secureapi;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@Tag("IntegrationTest")
@ActiveProfiles({"dev"})
class SecureApiAppTest {

    @Test
    void contextLoads() {
    }

}