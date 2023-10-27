package pod.tsu.spring.pokemon.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import pod.tsu.spring.pokemon.models.Pokemon;

import java.util.List;

@DataJpaTest
@ActiveProfiles({"dev"})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PokemonRepositoryTest {

    @Autowired
    private PokemonRepository repository;

    @Test
    public void repository_save_returnsSavedPokemon() {

        // Arrange
        Pokemon kabuto = Pokemon.builder().name("Kabuto").type("Water").build();

        // Act
        Pokemon savedPokemon = repository.save(kabuto);

        // Assert
        Assertions.assertThat(savedPokemon).isNotNull();
        Assertions.assertThat(savedPokemon.getId()).isGreaterThan(0);

    }

    @Test
    public void repository_findAll_returnsMoreThanOnePokemon() {

        // Arrange
        Pokemon gloom = Pokemon.builder().name("Gloom").type("Poison").build();
        Pokemon machop = Pokemon.builder().name("Machop").type("Fighting").build();
        repository.save(gloom);
        repository.save(machop);

        // Act
        List<Pokemon> pokemons = repository.findAll();
        pokemons.forEach(System.out::println);

        // Assert
        Assertions.assertThat(pokemons).isNotNull();
        Assertions.assertThat(pokemons.size()).isGreaterThan(2);

    }

}