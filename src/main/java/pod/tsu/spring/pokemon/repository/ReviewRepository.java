package pod.tsu.spring.pokemon.repository;

import pod.tsu.spring.pokemon.models.Review;

import java.util.Optional;

public interface ReviewRepository {

    Optional<Review> findById(int id);

}