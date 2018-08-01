package com.dima.services.implementation;

import com.dima.dao.DAOFactory;
import com.dima.models.entity.Review;
import com.dima.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private DAOFactory daoFactory;

    @Override
    public Review createReview(Review review) {
        return daoFactory.getReviewDAO().createReview(review);
    }

    @Override
    public List<Review> getAllReviews() {
        return daoFactory.getReviewDAO().getAllReviews();
    }

    @Override
    public Review getReviewById(int id) {
        return daoFactory.getReviewDAO().getReviewById(id);
    }

    @Override
    public Review updateReview(int id, Review review) {
        return daoFactory.getReviewDAO().updateReview(id, review);
    }

    @Override
    public ResponseEntity<Review> deleteReview(int id) {
        return daoFactory.getReviewDAO().deleteReview(id);
    }
}
