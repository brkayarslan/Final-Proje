package com.berkayarslan.UserService.controller.contract;

import com.berkayarslan.UserService.dto.ReviewDTO;
import com.berkayarslan.UserService.request.ReviewSaveRequest;
import com.berkayarslan.UserService.request.ReviewUpdateRequest;

import java.util.List;

public interface ReviewControllerContract {
    List<ReviewDTO> findAllReviews();
    ReviewDTO findReviewById(Long id);
    List<ReviewDTO> findReviewsByRestaurantId(String id);
    List<ReviewDTO> findReviewsByUserId(Long id);
    ReviewDTO saveReview(ReviewSaveRequest saveRequest);
    ReviewDTO updateReview(ReviewUpdateRequest updateRequest);
    void deleteReview(Long id);
}
