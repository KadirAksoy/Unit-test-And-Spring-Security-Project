package com.kadiraksoy.UnittestAndSpringSecurityProject.service;


import com.kadiraksoy.UnittestAndSpringSecurityProject.dto.ReviewDto;
import com.kadiraksoy.UnittestAndSpringSecurityProject.exception.MisketNotFoundException;
import com.kadiraksoy.UnittestAndSpringSecurityProject.exception.ReviewNotFoundException;
import com.kadiraksoy.UnittestAndSpringSecurityProject.model.Misket;
import com.kadiraksoy.UnittestAndSpringSecurityProject.model.Review;
import com.kadiraksoy.UnittestAndSpringSecurityProject.repository.MisketRepository;
import com.kadiraksoy.UnittestAndSpringSecurityProject.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MisketRepository misketRepository;

    public ReviewService(ReviewRepository reviewRepository, MisketRepository misketRepository) {
        this.reviewRepository = reviewRepository;
        this.misketRepository = misketRepository;
    }

    public ReviewDto createReview(int misketId,ReviewDto reviewDto){
        Review review = mapToEntity(reviewDto);

        Misket misket = misketRepository.findById(misketId).orElseThrow(() ->
                new MisketNotFoundException("Misket with associated review not found"));

        review.setMisket(misket);
        reviewRepository.save(review);
        return mapToDto(review);
    }

    public List<ReviewDto> getReviewsByMisketId(int id) {
        List<Review> reviews = reviewRepository.findByMisketId(id);

        return reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
    }

    public ReviewDto getReviewById(int reviewId, int misketId) {
        Misket misket = misketRepository.findById(misketId).orElseThrow(() ->
                new MisketNotFoundException("Misket with associated review not found"));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->
                new ReviewNotFoundException("Review with associate pokemon not found"));

        if(review.getMisket().getId() != misket.getId()) {
            throw new ReviewNotFoundException("This review does not belond to a misket");
        }
        return mapToDto(review);
    }

    public ReviewDto updateReview(int misketId, int reviewId, ReviewDto reviewDto) {
        Misket misket = misketRepository.findById(misketId).orElseThrow(() ->
                new MisketNotFoundException("Misket with associated review not found"));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->
                new ReviewNotFoundException("Review with associate pokemon not found"));

        if(review.getMisket().getId() != misket.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a pokemon");
        }
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        Review updateReview = reviewRepository.save(review);

        return mapToDto(updateReview);
    }

    public void deleteReview(int misketId, int reviewId) {
        Misket misket = misketRepository.findById(misketId).orElseThrow(() ->
                new MisketNotFoundException("Misket with associated review not found"));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->
                new ReviewNotFoundException("Review with associate pokemon not found"));

        if(review.getMisket().getId() != misket.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a pokemon");
        }
        reviewRepository.delete(review);
    }

    private ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }

    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        return review;
    }
}
