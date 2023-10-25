package pod.tsu.spring.pokemon.repository;

import pod.tsu.spring.pokemon.models.Pokemon;

import java.util.Optional;

public interface PokemonRepository {

    Optional<Pokemon> findById(int id);

}