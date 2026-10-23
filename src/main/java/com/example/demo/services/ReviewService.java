package com.example.demo.services;

import com.example.demo.entities.Review;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewService implements IReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review createReview( Review review) {
        return reviewRepository.save(review);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Review> getReviewById(Long id) throws NotFoundException {
        return Optional.ofNullable(reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Review Not found with id :" + id)));
    }

    @Override
    @Transactional
    public Double getAverageRating() {
        List<Review> reviews = reviewRepository.findAll();
        if (reviews.isEmpty()) {
            return 0.0;
        }
        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }

    @Override
    public void deleteReview(Long id) throws NotFoundException {
        if(!reviewRepository.existsById(id)) {
            throw new NotFoundException("Review not fount with ID : " + id);
        }
        reviewRepository.deleteById(id);
    }
}
