package com.berkayarslan.UserService.controller;

import com.berkayarslan.UserService.controller.contract.ReviewControllerContract;
import com.berkayarslan.UserService.dto.ReviewDTO;
import com.berkayarslan.UserService.general.RestResponse;
import com.berkayarslan.UserService.request.ReviewSaveRequest;
import com.berkayarslan.UserService.request.ReviewUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewControllerContract reviewControllerContract;

    public ReviewController(ReviewControllerContract reviewControllerContract) {
        this.reviewControllerContract = reviewControllerContract;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<ReviewDTO>>> findAllReviews(){
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.findAllReviews()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<ReviewDTO>> findById(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.findReviewById(id)));
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<RestResponse<List<ReviewDTO>>> findByUserId(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.findReviewsByUserId(id)));
    }

    @GetMapping("/restaurantId/{id}")
    public ResponseEntity<RestResponse<List<ReviewDTO>>> findByRestaurantId(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.findReviewsByRestaurantId(id)));
    }

    @PostMapping
    public ResponseEntity<RestResponse<ReviewDTO>> saveReview(ReviewSaveRequest saveRequest){
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.saveReview(saveRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<ReviewDTO>> updateReview(ReviewUpdateRequest updateRequest){
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.updateReview(updateRequest)));
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id){
        reviewControllerContract.deleteReview(id);
    }
}
