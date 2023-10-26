package pod.tsu.spring.pokemon.repository;

import org.springframework.data.repository.CrudRepository;
import pod.tsu.spring.pokemon.models.Pokemon;

public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {
}