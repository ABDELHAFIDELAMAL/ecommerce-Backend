package com.example.demo.services;

import com.example.demo.entities.Review;
import com.example.demo.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface IReviewService {
    Review createReview(Review review);
    List<Review> getAllReviews();
    Optional<Review> getReviewById(Long id) throws NotFoundException;
    Double getAverageRating();
    void deleteReview(Long id) throws NotFoundException;
}
