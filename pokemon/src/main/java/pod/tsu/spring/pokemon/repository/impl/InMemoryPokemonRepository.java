package pod.tsu.spring.pokemon.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pod.tsu.spring.pokemon.models.Pokemon;
import pod.tsu.spring.pokemon.repository.PokemonRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class InMemoryPokemonRepository implements PokemonRepository {

    private final Logger logger = LoggerFactory.getLogger(InMemoryPokemonRepository.class);
    private Set<Pokemon> pokemons = new HashSet<>();

    public InMemoryPokemonRepository() {
        logger.info("Created");
    }

    @Override
    public Optional<Pokemon> findById(int id) {
        return pokemons.stream()
            .filter(pokemon -> pokemon.getId() == id)
            .findFirst();
    }

}