package br.com.biptag.biptag_api.controller;

import br.com.biptag.biptag_api.model.Review;
import br.com.biptag.biptag_api.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(service.getAllReviews());
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review newReview = service.createReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(newReview);
    }
}