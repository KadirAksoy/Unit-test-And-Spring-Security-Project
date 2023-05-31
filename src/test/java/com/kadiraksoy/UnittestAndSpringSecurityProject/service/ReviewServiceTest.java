package com.kadiraksoy.UnittestAndSpringSecurityProject.service;

import com.kadiraksoy.UnittestAndSpringSecurityProject.dto.MisketDto;
import com.kadiraksoy.UnittestAndSpringSecurityProject.dto.ReviewDto;
import com.kadiraksoy.UnittestAndSpringSecurityProject.model.Misket;
import com.kadiraksoy.UnittestAndSpringSecurityProject.model.Review;
import com.kadiraksoy.UnittestAndSpringSecurityProject.repository.MisketRepository;
import com.kadiraksoy.UnittestAndSpringSecurityProject.repository.ReviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {


    private MisketRepository misketRepository;
    private ReviewRepository reviewRepository;
    private ReviewService reviewService;

    private Misket misket;
    private Review review;
    private MisketDto misketDto;
    private ReviewDto reviewDto;


    @BeforeEach
    public void setup() throws Exception{
        misketRepository = Mockito.mock(MisketRepository.class);
        reviewRepository = Mockito.mock(ReviewRepository.class);
        reviewService = new ReviewService(reviewRepository,misketRepository);

    }

    // We can define the data in the first place like this.
    @BeforeEach
    public void init() {
        misket = Misket.builder().name("blue").type("hit").build();
        misketDto = MisketDto.builder().name("blue").type("hit").build();
        review = Review.builder().title("title").content("content").stars(5).build();
        reviewDto = ReviewDto.builder().title("title").content("content").stars(5).build();

    }

    @Test
    public void ReviewService_CreateReview_ReturnsReviewDto() {


        Mockito.when(misketRepository.findById(misket.getId())).thenReturn(Optional.of(misket));   //repository function
        Mockito.when(reviewRepository.save(Mockito.any(Review.class))).thenReturn(review);

        ReviewDto savedReview = reviewService.createReview(misket.getId(), reviewDto);      // service function

        Assertions.assertEquals(reviewDto,savedReview);                             // Assert

        Mockito.verify(misketRepository).findById(misket.getId());               //verify
    }

    @Test
    public void ReviewService_GetReviewsByMisketId_ReturnReviewDto() {
        int misketId = 1;
        Mockito.when(reviewRepository.findByMisketId(misketId)).thenReturn(Arrays.asList(review));

        List<ReviewDto> reviewDtos = reviewService.getReviewsByMisketId(misketId);

        org.assertj.core.api.Assertions.assertThat(reviewDtos).isNotNull();
    }


    @Test
    public void ReviewService_GetReviewById_ReturnReviewDto() {
        int reviewId = 0;
        int misketId= 0;

        review.setMisket(misket);

        Mockito.when(misketRepository.findById(misketId)).thenReturn(Optional.of(misket));
        Mockito.when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        ReviewDto reviewReturn = reviewService.getReviewById(reviewId, misketId);

        Assertions.assertEquals(reviewDto,reviewReturn);

        Mockito.verify(misketRepository).findById(misketId);
        Mockito.verify(reviewRepository).findById(reviewId);
    }

    @Test
    public void ReviewService_UpdateMisket_ReturnReviewDto() {
        int misketId = 5;
        int reviewId = 5;
        misket.setReviews(Arrays.asList(review));
        review.setMisket(misket);

        Mockito.when(misketRepository.findById(misketId)).thenReturn(Optional.of(misket));
        Mockito.when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
        Mockito.when(reviewRepository.save(review)).thenReturn(review);

        ReviewDto updateReturn = reviewService.updateReview(misketId, reviewId, reviewDto);

        Assertions.assertEquals(reviewDto,updateReturn);

        Mockito.verify(misketRepository).findById(misketId);
        Mockito.verify(reviewRepository).findById(reviewId);
        Mockito.verify(reviewRepository).save(review);
    }

//    @Test
//    public void ReviewService_DeleteReviewByMisketId_ReturnVoid() {
//        int misketId = 1;
//        int reviewId = 1;
//
//        misket.setReviews(Arrays.asList(review));
//        review.setId(reviewId);
//        review.setMisket(misket);
//
//        Mockito.when(misketRepository.findById(misketId)).thenReturn(Optional.of(misket));
//        Mockito.when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
//
//
//    }


}