package pod.tsu.spring.pokemon.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import pod.tsu.spring.pokemon.models.Pokemon;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Tag("UnitTest")
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

        // Assert
        Assertions.assertThat(pokemons).isNotNull();
        Assertions.assertThat(pokemons.size()).isGreaterThan(2);

    }

    @Test
    public void repository_findById_returnsSavedPokemon() {

        // Arrange
        Pokemon onix = Pokemon.builder().name("Onix").type("Rock").build();
        repository.save(onix);
        Pokemon savedPokemon = repository.save(onix);

        // Act
        Optional<Pokemon> result = repository.findById(onix.getId());

        // Assert
        Assertions.assertThat(result.isPresent()).isTrue();
        Assertions.assertThat(result.get().getName()).isEqualTo("Onix");

    }

    @Test
    public void repository_findByType_returnsMoreThanOnePokemon() {

        // Arrange
        Pokemon scizor = Pokemon.builder().name("Scizor").type("Bug").build();
        Pokemon beedrill = Pokemon.builder().name("Beedrill").type("Bug").build();
        repository.save(scizor);
        repository.save(scizor);

        // Act
        List<Pokemon> pokemons = repository.findByTpe("Bug");

        // Assert
        Assertions.assertThat(pokemons).isNotNull();

    }

    @Test
    public void repository_update_modifiesPokemon() {

        // Arrange
        Pokemon venonat = Pokemon.builder().name("Venonat").type("Bug").build();
        repository.save(venonat);

        Optional<Pokemon> result1 = repository.findById(venonat.getId());
        assertTrue(result1.isPresent());
        assertEquals("Bug", result1.get().getType());
        Pokemon foundPokemon = result1.get();

        // Act
        foundPokemon.setType("Poison");
        repository.save(foundPokemon);

        // Assert
        Optional<Pokemon> result2 = repository.findById(venonat.getId());
        assertTrue(result2.isPresent());
        assertEquals("Poison", result2.get().getType());

    }

    @Test
    public void repository_delete_removesPokemon() {

        // Arrange
        Pokemon vulpix = Pokemon.builder().name("Vulpix").type("Fire").build();
        repository.save(vulpix);

        Optional<Pokemon> result1 = repository.findById(vulpix.getId());
        assertTrue(result1.isPresent());
        Pokemon foundPokemon = result1.get();

        // Act
        repository.delete(foundPokemon);

        // Assert
        Optional<Pokemon> result2 = repository.findById(vulpix.getId());
        assertFalse(result2.isPresent());

    }

}