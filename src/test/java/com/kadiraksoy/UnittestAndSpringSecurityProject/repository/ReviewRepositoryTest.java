package com.kadiraksoy.UnittestAndSpringSecurityProject.repository;

import com.kadiraksoy.UnittestAndSpringSecurityProject.model.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void whenReviewRepositorySaveAllReview_thenReturnReview(){
        Review review = Review.builder().title("title").content("content").stars(5).build();

        Review except = reviewRepository.save(review);

        Assertions.assertEquals(except,review);
    }

    @Test
    public void whenReviewRepositoryGetAllReview_thenReturnIsNotNull(){
        Review review = Review.builder().title("title").content("content").stars(5).build();
        Review review2 = Review.builder().title("title2").content("content2").stars(5).build();

        reviewRepository.save(review);
        reviewRepository.save(review2);

        List<Review> reviewList = reviewRepository.findAll();

        org.assertj.core.api.Assertions.assertThat(reviewList).isNotNull();
        org.assertj.core.api.Assertions.assertThat(reviewList.size()).isEqualTo(2);
    }

    @Test
    public void whenReviewRepositoryFindByReviewId_thenReturnReview(){
        Review review = Review.builder().title("title").content("content").stars(5).build();

        reviewRepository.save(review);

        Review except = reviewRepository.findById(review.getId()).get();

        Assertions.assertEquals(except, review);
    }

    @Test
    public void whenReviewRepositoryUpdateReviewWithReviewId_thenIsNotNull(){
        Review review = Review.builder().title("title").content("content").stars(5).build();

        reviewRepository.save(review);

        Review reviewSave = reviewRepository.findById(review.getId()).get();
        reviewSave.setTitle("title");
        reviewSave.setContent("content");
        Review updatedReview = reviewRepository.save(reviewSave);

        org.assertj.core.api.Assertions.assertThat(updatedReview.getTitle()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(updatedReview.getContent()).isNotNull();
    }

    @Test
    public void whenReviewRepositoryDeleteReviewWithReviewId_thenReturnIsEmpty(){
        Review review = Review.builder().title("title").content("content").stars(5).build();

        reviewRepository.save(review);

        reviewRepository.deleteById(review.getId());
        Optional<Review> reviewReturn = reviewRepository.findById(review.getId());

        org.assertj.core.api.Assertions.assertThat(reviewReturn).isEmpty();
    }

}