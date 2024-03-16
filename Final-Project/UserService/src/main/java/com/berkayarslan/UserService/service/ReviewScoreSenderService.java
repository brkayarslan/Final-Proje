package com.berkayarslan.UserService.service;


import com.berkayarslan.UserService.client.RestaurantClient;
import com.berkayarslan.UserService.dto.ReviewScoreDTO;
import com.berkayarslan.UserService.dto.ReviewUpdateScoreDTO;
import com.berkayarslan.UserService.exceptions.ItemNotFoundException;
import com.berkayarslan.UserService.general.GeneralErrorMessage;
import com.berkayarslan.UserService.general.RestResponse;
import com.berkayarslan.UserService.model.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReviewScoreSenderService {

    private final RestaurantClient restaurantClient;

    public ReviewScoreSenderService(RestaurantClient restaurantClient) {
        this.restaurantClient = restaurantClient;
    }

    public boolean sendReviewScoresToRestaurant(Review review){
        ReviewScoreDTO reviewScoreDTO = new ReviewScoreDTO(review.getFoodScore(),
                review.getPresentationScore(),
                review.getDeliveryScore());
        ResponseEntity<RestResponse<Boolean>> restResponseResponseEntity = restaurantClient.sendRestaurantScores(review.getRestaurantId(), reviewScoreDTO);
        if (!restResponseResponseEntity.getBody().getData().equals(true)){
            throw new ItemNotFoundException(GeneralErrorMessage.ITEM_NOT_FOUND);
        }
        return true;
    }

    public boolean sendUpdateReviewScoreToRestaurant(String restaurantId, ReviewUpdateScoreDTO reviewUpdateScoreDTO){
        ResponseEntity<RestResponse<Boolean>> restResponseResponseEntity = restaurantClient.sendUpdateRestaurantReview(restaurantId, reviewUpdateScoreDTO);
        if (!restResponseResponseEntity.getBody().getData().equals(true)){
            throw new ItemNotFoundException(GeneralErrorMessage.ITEM_NOT_FOUND);
        }
        return true;
    }
}
