package pod.tsu.spring.pokemon;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Tag("IntegrationTest")
@ActiveProfiles({"dev"})
class PokemonAppTest {

    @Test
    void contextLoads() {
    }

}