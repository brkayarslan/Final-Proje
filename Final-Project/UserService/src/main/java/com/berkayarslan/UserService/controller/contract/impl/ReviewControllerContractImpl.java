package com.berkayarslan.UserService.controller.contract.impl;

import com.berkayarslan.UserService.client.RestaurantClient;
import com.berkayarslan.UserService.controller.contract.ReviewControllerContract;
import com.berkayarslan.UserService.dto.ReviewDTO;
import com.berkayarslan.UserService.dto.ReviewScoreDTO;
import com.berkayarslan.UserService.dto.ReviewUpdateScoreDTO;
import com.berkayarslan.UserService.exceptions.ItemNotFoundException;
import com.berkayarslan.UserService.general.GeneralErrorMessage;
import com.berkayarslan.UserService.general.RestResponse;
import com.berkayarslan.UserService.mapper.ReviewConverter;
import com.berkayarslan.UserService.model.Review;
import com.berkayarslan.UserService.model.User;
import com.berkayarslan.UserService.request.ReviewSaveRequest;
import com.berkayarslan.UserService.request.ReviewUpdateRequest;
import com.berkayarslan.UserService.service.ReviewScoreSenderService;
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
    private final ReviewScoreSenderService reviewScoreSenderService;

    public ReviewControllerContractImpl(ReviewService reviewService, ReviewConverter reviewConverter, UserService userService, ReviewScoreSenderService reviewScoreSenderService) {
        this.reviewService = reviewService;
        this.reviewConverter = reviewConverter;
        this.userService = userService;
        this.reviewScoreSenderService = reviewScoreSenderService;
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
        boolean b = reviewScoreSenderService.sendReviewScoresToRestaurant(review);
        if(b){
            review = reviewService.save(review);
        }
        return reviewConverter.convertToDTO(review);
    }

    @Override
    public ReviewDTO updateReview(ReviewUpdateRequest updateRequest) {
        Review review = reviewService.findByIdWithControl(updateRequest.id());
        boolean isUpdateSuccessful  = false;
        if (!review.getFoodScore().equals(updateRequest.foodScore()) || !review.getDeliveryScore().equals(updateRequest.deliveryScore()) || !review.getPresentationScore().equals(updateRequest.presentationScore())){
            ReviewUpdateScoreDTO reviewUpdateScoreDTO = new ReviewUpdateScoreDTO(review.getFoodScore(),
                                                                                 review.getPresentationScore(),
                                                                                 review.getDeliveryScore(),
                                                                                 updateRequest.foodScore(),
                                                                                 updateRequest.presentationScore(),
                                                                                 updateRequest.deliveryScore());
            isUpdateSuccessful  = reviewScoreSenderService.sendUpdateReviewScoreToRestaurant(review.getRestaurantId(), reviewUpdateScoreDTO);
        }

        try{
            if (isUpdateSuccessful ){
                review = reviewConverter.updateReview(review,updateRequest);
                review = reviewService.save(review);
            }
        }catch (Exception e){
            ReviewUpdateScoreDTO reviewRevertScoreDTO = new ReviewUpdateScoreDTO(updateRequest.foodScore(),
                                                                                 updateRequest.presentationScore(),
                                                                                 updateRequest.deliveryScore(),
                                                                                 review.getFoodScore(),
                                                                                 review.getPresentationScore(),
                                                                                 review.getDeliveryScore());
            reviewScoreSenderService.sendUpdateReviewScoreToRestaurant(review.getRestaurantId(),reviewRevertScoreDTO);
            throw new RuntimeException("Review update process failed.",e);
        }

        return reviewConverter.convertToDTO(review);
    }

    @Override
    public void deleteReview(Long id) {
        reviewService.delete(id);
    }
}
