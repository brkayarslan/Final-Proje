package com.berkayarslan.UserService.controller.contract.impl;

import com.berkayarslan.UserService.client.RestaurantClient;
import com.berkayarslan.UserService.dto.ReviewDTO;
import com.berkayarslan.UserService.mapper.ReviewConverter;
import com.berkayarslan.UserService.model.Review;
import com.berkayarslan.UserService.model.User;
import com.berkayarslan.UserService.request.ReviewSaveRequest;
import com.berkayarslan.UserService.request.ReviewUpdateRequest;
import com.berkayarslan.UserService.service.ReviewScoreSenderService;
import com.berkayarslan.UserService.service.ReviewService;
import com.berkayarslan.UserService.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewControllerContractImplTest {

    @Mock
    private ReviewService reviewService;
    @Mock
    private UserService userService;

    @Mock
    private ReviewScoreSenderService reviewScoreSenderService;
    @Mock
    private ReviewConverter reviewConverter;

    @InjectMocks
    private ReviewControllerContractImpl reviewControllerContract;
    @Test
    void shouldSaveReview() {
        ReviewSaveRequest reviewSaveRequest = new ReviewSaveRequest(1L,
                                                                    "restaurantId",
                                                                    5 ,
                                                                    5 ,
                                                                    5 ,
                                                                    "comment");

        User user = new User();
        Review review =  new Review(user,
                                    "restaurantId",
                                    5 ,
                                    5 ,
                                    5 ,
                                    "comment",
                                    LocalDate.now());
        Review savedReview = new Review(1L,
                                         user,
                                         "restaurantId",
                                         5 ,
                                         5 ,
                                         5 ,
                                         "comment",
                                         LocalDate.now());
        ReviewDTO expectedDTO  = new ReviewDTO(1L,
                user.getFirstName(),
                "restaurantId",
                5 ,
                5 ,
                5 ,
                "comment",
                LocalDate.now());
        when(userService.findByIdWithControl(1L)).thenReturn(user);
        when(reviewConverter.convertToReview(user,reviewSaveRequest)).thenReturn(review);
        when(reviewScoreSenderService.sendReviewScoresToRestaurant(review)).thenReturn(true);
        when(reviewService.save(any(Review.class))).thenReturn(savedReview);


        ReviewDTO resultDTO = reviewControllerContract.saveReview(reviewSaveRequest);

        verify(userService).findByIdWithControl(reviewSaveRequest.userId());
        verify(reviewService).save(review);
    }

    @Test
    void shouldUpdateReview() {
        ReviewUpdateRequest reviewUpdateRequest = new ReviewUpdateRequest(1L,
                5 ,
                5 ,
                5 ,
                "comment");
        User user = new User();
        Review review = new Review(user,
                "restaurantId",
                5 ,
                5 ,
                5 ,
                "comment",
                LocalDate.now());
    }
}