package br.com.biptag.biptag_api.service;

import br.com.biptag.biptag_api.model.Review;
import br.com.biptag.biptag_api.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    // Equivale a police de SELECT do Supabase
    public List<Review> getAllReviews() {
        return repository.findAll();
    }

    // Equivale a police de INSERT do Supabase
    public Review createReview(Review review) {
        return repository.save(review);
    }
}