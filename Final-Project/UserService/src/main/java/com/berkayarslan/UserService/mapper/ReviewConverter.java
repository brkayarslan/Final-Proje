package com.berkayarslan.UserService.mapper;

import com.berkayarslan.UserService.dto.ReviewDTO;
import com.berkayarslan.UserService.model.Review;
import com.berkayarslan.UserService.model.User;
import com.berkayarslan.UserService.request.ReviewSaveRequest;
import com.berkayarslan.UserService.request.ReviewUpdateRequest;
import com.berkayarslan.UserService.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewConverter {


    public ReviewDTO convertToDTO(Review review){
        return new ReviewDTO(review.getId(),
                                            review.getUser().getFirstName(),
                                            review.getRestaurantId(),
                                            review.getFoodScore(),
                                            review.getPresentationScore(),
                                            review.getDeliveryScore(),
                                            review.getComment(),
                                            review.getReviewDate());
    }

    public List<ReviewDTO> convertToDTOList(List<Review> reviews){
        return reviews.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Review convertToReview(User user, ReviewSaveRequest saveRequest) {
        return new Review(user,
                          saveRequest.restaurantId(),
                          saveRequest.foodScore(),
                          saveRequest.presentationScore(),
                          saveRequest.deliveryScore(),
                          saveRequest.comment(),
                          LocalDate.now());
    }

    public Review updateReview(Review review,ReviewUpdateRequest reviewUpdateRequest){
        if(reviewUpdateRequest == null){
            return null;
        }
        review.setFoodScore(reviewUpdateRequest.foodScore());
        review.setPresentationScore(reviewUpdateRequest.presentationScore());
        review.setDeliveryScore(reviewUpdateRequest.deliveryScore());
        review.setComment(reviewUpdateRequest.comment());

        return review;
    }
}
