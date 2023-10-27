package pod.tsu.spring.pokemon;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"dev"})
class PokemonAppTest {

    @Test
    void contextLoads() {
    }

}