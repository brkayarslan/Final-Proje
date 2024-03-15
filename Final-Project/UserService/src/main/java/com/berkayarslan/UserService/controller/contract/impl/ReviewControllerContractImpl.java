package com.berkayarslan.UserService.controller.contract.impl;

import com.berkayarslan.UserService.client.RestaurantClient;
import com.berkayarslan.UserService.controller.contract.ReviewControllerContract;
import com.berkayarslan.UserService.dto.ReviewDTO;
import com.berkayarslan.UserService.dto.ReviewScoreDTO;
import com.berkayarslan.UserService.exceptions.ItemNotFoundException;
import com.berkayarslan.UserService.general.GeneralErrorMessage;
import com.berkayarslan.UserService.general.RestResponse;
import com.berkayarslan.UserService.mapper.ReviewConverter;
import com.berkayarslan.UserService.model.Review;
import com.berkayarslan.UserService.model.User;
import com.berkayarslan.UserService.request.ReviewSaveRequest;
import com.berkayarslan.UserService.request.ReviewUpdateRequest;
import com.berkayarslan.UserService.service.ReviewService;
import com.berkayarslan.UserService.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewControllerContractImpl implements ReviewControllerContract {

    private final ReviewService reviewService;
    private final ReviewConverter reviewConverter;
    private final UserService userService;
    private final RestaurantClient restaurantClient;

    public ReviewControllerContractImpl(ReviewService reviewService, ReviewConverter reviewConverter, UserService userService, RestaurantClient restaurantClient) {
        this.reviewService = reviewService;
        this.reviewConverter = reviewConverter;
        this.userService = userService;
        this.restaurantClient = restaurantClient;
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
    public List<ReviewDTO> findReviewsByRestaurantId(String id) {
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
        sendReviewScoresToRestaurant(review);
        return reviewConverter.convertToDTO(review);
    }

    private void sendReviewScoresToRestaurant(Review review){
        ReviewScoreDTO reviewScoreDTO = new ReviewScoreDTO(review.getFoodScore(),
                                                           review.getPresentationScore(),
                                                           review.getDeliveryScore());
        ResponseEntity<RestResponse<Boolean>> restResponseResponseEntity = restaurantClient.sendRestaurantScores(review.getRestaurantId(), reviewScoreDTO);
        if (!restResponseResponseEntity.getBody().getData().equals(true)){
            throw new ItemNotFoundException(GeneralErrorMessage.ITEM_NOT_FOUND);
        }
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
