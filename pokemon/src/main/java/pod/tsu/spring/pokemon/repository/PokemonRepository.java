package pod.tsu.spring.pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pod.tsu.spring.pokemon.models.Pokemon;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

    @Query(value = "SELECT p FROM Pokemon p WHERE p.type = :type")
    List<Pokemon> findByTpe(@Param("type") String type);

}