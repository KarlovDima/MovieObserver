package com.dima.dao.implementation;

import com.dima.dao.ResourceNotFoundException;
import com.dima.dao.repositories.ReviewRepository;
import com.dima.models.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewDAO {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review getReviewById(int id) {
        return reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review", "id", id));
    }

    public Review updateReview(int id, Review updatedReview) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", id));

        review.setFilm(updatedReview.getFilm());
        review.setCritic(updatedReview.getCritic());
        review.setComment(updatedReview.getComment());

        return reviewRepository.save(review);
    }

    public ResponseEntity deleteReview(int id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review", "id", id));
        reviewRepository.delete(review);

        return ResponseEntity.ok().build();
    }
}

