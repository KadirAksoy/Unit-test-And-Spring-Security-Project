package com.kadiraksoy.UnittestAndSpringSecurityProject.controller;

import com.kadiraksoy.UnittestAndSpringSecurityProject.dto.ReviewDto;
import com.kadiraksoy.UnittestAndSpringSecurityProject.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/misket/{misketId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "misketId") int misketId, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.createReview(misketId, reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/misket/{misketId}/reviews")
    public List<ReviewDto> getReviewsByPokemonId(@PathVariable(value = "misketId") int misketId) {
        return reviewService.getReviewsByMisketId(misketId);
    }

    @GetMapping("/misket/{misketId}/reviews/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value = "misketId") int misketId, @PathVariable(value = "id") int reviewId) {
        ReviewDto reviewDto = reviewService.getReviewById(misketId, reviewId);
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }

    @PutMapping("/misket/{misketId}/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "misketId") int misketId, @PathVariable(value = "id") int reviewId,
                                                  @RequestBody ReviewDto reviewDto) {
        ReviewDto updatedReview = reviewService.updateReview(misketId, reviewId, reviewDto);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/misket/{misketId}/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "misketId") int misketId, @PathVariable(value = "id") int reviewId) {
        reviewService.deleteReview(misketId, reviewId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }
}
