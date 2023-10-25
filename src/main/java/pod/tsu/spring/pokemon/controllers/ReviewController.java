package pod.tsu.spring.pokemon.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pod.tsu.spring.pokemon.dto.ReviewDto;
import pod.tsu.spring.pokemon.service.ReviewService;

@RestController
@RequestMapping("/app")
public class ReviewController {

    private final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
        logger.info("Created");
    }

    @GetMapping("/pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<ReviewDto> getReviewById(
        @PathVariable(value = "pokemonId") int pokemonId,
        @PathVariable(value = "id") int reviewId
    ) {
        ReviewDto reviewDto = reviewService.getReviewById(pokemonId, reviewId);
        return ResponseEntity.ok(reviewDto);
    }

}