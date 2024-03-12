package com.berkayarslan.UserService.controller.contract.impl;

import com.berkayarslan.UserService.controller.contract.ReviewControllerContract;
import com.berkayarslan.UserService.dto.ReviewDTO;
import com.berkayarslan.UserService.mapper.ReviewConverter;
import com.berkayarslan.UserService.mapper.ReviewMapper;
import com.berkayarslan.UserService.model.Review;
import com.berkayarslan.UserService.model.User;
import com.berkayarslan.UserService.request.ReviewSaveRequest;
import com.berkayarslan.UserService.request.ReviewUpdateRequest;
import com.berkayarslan.UserService.service.ReviewService;
import com.berkayarslan.UserService.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewControllerContractImpl implements ReviewControllerContract {

    private final ReviewService reviewService;
    private final ReviewConverter reviewConverter;
    private final UserService userService;

    public ReviewControllerContractImpl(ReviewService reviewService, ReviewConverter reviewConverter, UserService userService) {
        this.reviewService = reviewService;
        this.reviewConverter = reviewConverter;
        this.userService = userService;
    }

    @Override
    public List<ReviewDTO> findAllReviews() {
        return reviewConverter.convertToDTOList(reviewService.findAll());
    }

    @Override
    public ReviewDTO findReviewById(Long id) {
        return reviewConverter.convertToDTO(reviewService.findByIdWithControl(id));
    }

    @Override
    public List<ReviewDTO> findReviewsByRestaurantId(Long id) {
        return reviewConverter.convertToDTOList(reviewService.findAllByRestaurantId(id));
    }

    @Override
    public List<ReviewDTO> findReviewsByUserId(Long id) {
        return reviewConverter.convertToDTOList(reviewService.findAllByUserId(id));
    }

    @Override
    public ReviewDTO saveReview(ReviewSaveRequest saveRequest) {
        User user = userService.findByIdWithControl(saveRequest.userId());
        Review review = reviewConverter.convertToReview(user,saveRequest);
        review = reviewService.save(review);
        return reviewConverter.convertToDTO(review);
    }

    @Override
    public ReviewDTO updateReview(ReviewUpdateRequest updateRequest) {
        Review review = reviewService.findByIdWithControl(updateRequest.id());
        review = reviewConverter.updateReview(review,updateRequest);
        review = reviewService.save(review);
        return reviewConverter.convertToDTO(review);
    }

    @Override
    public void deleteReview(Long id) {
        reviewService.delete(id);
    }
}
