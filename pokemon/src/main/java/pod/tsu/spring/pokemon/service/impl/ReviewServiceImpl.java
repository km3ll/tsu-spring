package pod.tsu.spring.pokemon.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pod.tsu.spring.pokemon.dto.ReviewDto;
import pod.tsu.spring.pokemon.exceptions.PokemonNotFoundException;
import pod.tsu.spring.pokemon.exceptions.ReviewNotFoundException;
import pod.tsu.spring.pokemon.models.Pokemon;
import pod.tsu.spring.pokemon.models.Review;
import pod.tsu.spring.pokemon.repository.PokemonRepository;
import pod.tsu.spring.pokemon.repository.ReviewRepository;
import pod.tsu.spring.pokemon.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;

    private ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
        logger.info("Created");
    }

    @Override
    public ReviewDto getReviewById(int reviewId, int pokemonId) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with associate pokemon not found"));
        if(review.getPokemon().getId() != pokemon.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a pokemon");
        }
        return mapToDto(review);

    }

}
