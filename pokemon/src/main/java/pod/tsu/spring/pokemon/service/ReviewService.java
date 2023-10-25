package pod.tsu.spring.pokemon.service;

import pod.tsu.spring.pokemon.dto.ReviewDto;

public interface ReviewService {

    //ReviewDto createReview(int pokemonId, ReviewDto reviewDto);

    //List<ReviewDto> getReviewsByPokemonId(int id);

    ReviewDto getReviewById(int reviewId, int pokemonId);

    //ReviewDto updateReview(int pokemonId, int reviewId, ReviewDto reviewDto);

    //void deleteReview(int pokemonId, int reviewId);

}