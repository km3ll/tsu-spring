package pod.tsu.spring.pokemon.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pod.tsu.spring.pokemon.models.Review;
import pod.tsu.spring.pokemon.repository.ReviewRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class InMemoryReviewRepository implements ReviewRepository {

    private final Logger logger = LoggerFactory.getLogger(InMemoryReviewRepository.class);

    private Set<Review> reviews = new HashSet<>();

    public InMemoryReviewRepository() {
        logger.info("Created");
    }

    @Override
    public Optional<Review> findById(int id) {
        return reviews.stream()
            .filter(review -> review.getId() == id)
            .findFirst();
    }

}