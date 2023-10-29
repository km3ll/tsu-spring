package testkit;

import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import pod.tsu.spring.pokemon.dto.PokemonDto;
import pod.tsu.spring.pokemon.models.Pokemon;

import java.util.Set;

public final class Generators {

    private static final Random random = new DefaultRandom();

    public static Pokemon pokemon() {
        Set<Pokemon> pokemons = Set.of(
            Pokemon.builder().name("Beedrill").type("Bug").build(),
            Pokemon.builder().name("Gloom").type("Poison").build(),
            Pokemon.builder().name("Kabuto").type("Water").build(),
            Pokemon.builder().name("Machop").type("Fighting").build(),
            Pokemon.builder().name("Onix").type("Rock").build(),
            Pokemon.builder().name("Scizor").type("Bug").build(),
            Pokemon.builder().name("Venonat").type("Bug").build(),
            Pokemon.builder().name("Vulpix").type("Fire").build()
        );
        return random.oneOf(pokemons);
    }

    public static PokemonDto pokemonDto() {
        Pokemon pokemon = pokemon();
        return PokemonDto.builder()
            .name(pokemon.getName())
            .type(pokemon.getType())
            .build();
    }

    public static PokemonDto pokemonDto(Pokemon pokemon) {
        return PokemonDto.builder()
            .name(pokemon.getName())
            .type(pokemon.getType())
            .build();
    }

}
