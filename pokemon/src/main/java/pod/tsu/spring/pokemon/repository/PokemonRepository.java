package pod.tsu.spring.pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pod.tsu.spring.pokemon.models.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
}