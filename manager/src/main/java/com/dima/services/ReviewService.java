package com.dima.services;

import com.dima.models.entity.Review;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {
    Review createReview(Review review);

    List<Review> getAllReviews();

    Review getReviewById(int id);

    Review updateReview(int id, Review review);

    ResponseEntity<Review> deleteReview(int id);
}
