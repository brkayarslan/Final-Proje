package com.berkayarslan.UserService.controller;

import com.berkayarslan.UserService.controller.contract.ReviewControllerContract;
import com.berkayarslan.UserService.dto.ReviewDTO;
import com.berkayarslan.UserService.general.RestResponse;
import com.berkayarslan.UserService.request.ReviewSaveRequest;
import com.berkayarslan.UserService.request.ReviewUpdateRequest;
import com.berkayarslan.UserService.request.UserSaveRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@Tag(name = "Review Controller", description = "Provides endpoints for managing reviews, including operations for retrieving, creating, updating, and deleting reviews.")
public class ReviewController {

    private final ReviewControllerContract reviewControllerContract;

    public ReviewController(ReviewControllerContract reviewControllerContract) {
        this.reviewControllerContract = reviewControllerContract;
    }

    @Operation(summary = "Get all reviews", description = "Retrieves a list of all reviews from the database.")
    @GetMapping
    public ResponseEntity<RestResponse<List<ReviewDTO>>> findAllReviews(){
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.findAllReviews()));
    }

    @Operation(summary = "Get review by ID", description = "Retrieves a specific review by its unique ID.")
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<ReviewDTO>> findById(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.findReviewById(id)));
    }

    @Operation(summary = "Get reviews by user ID", description = "Retrieves all reviews submitted by a specific user, identified by their user ID.")
    @GetMapping("/userId/{id}")
    public ResponseEntity<RestResponse<List<ReviewDTO>>> findByUserId(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.findReviewsByUserId(id)));
    }

    @Operation(summary = "Get reviews by restaurant ID", description = "Retrieves all reviews associated with a specific restaurant, identified by the restaurant's ID.")
    @GetMapping("/restaurantId/{id}")
    public ResponseEntity<RestResponse<List<ReviewDTO>>> findByRestaurantId(@PathVariable String id){
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.findReviewsByRestaurantId(id)));
    }

    @Operation(
            description = "Creates new review",
            summary = "Create Review",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Reviews",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ReviewSaveRequest.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new review",
                                                    summary = "Default",
                                                    description = "Complete request with all available fields for review",
                                                    value = "{\n"
                                                            + "  \"userId\": \"28\",\n"
                                                            + "  \"restaurantId\": \"9d7b6d21-3bc2-4513-b9a9-7ef900a60d96\",\n"
                                                            + "  \"foodScore\": \"5\",\n"
                                                            + "  \"presentationScore\": \"4\",\n"
                                                            + "  \"deliveryScore\": \"2\",\n"
                                                            + "  \"comment\": \"delicious\",\n"
                                                            + "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PostMapping
    public ResponseEntity<RestResponse<ReviewDTO>> saveReview(ReviewSaveRequest saveRequest){
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.saveReview(saveRequest)));
    }

    @Operation(
            description = "Updates old review",
            summary = "Update Review",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Reviews",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UserSaveRequest.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "update review",
                                                    summary = "Default",
                                                    description = "Complete request with all available fields for review",
                                                    value = "{\n"
                                                            + "  \"id\": \"35\",\n"
                                                            + "  \"foodScore\": \"5\",\n"
                                                            + "  \"presentationScore\": \"4\",\n"
                                                            + "  \"deliveryScore\": \"2\",\n"
                                                            + "  \"comment\": \"I don't like\",\n"
                                                            + "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PutMapping()
    public ResponseEntity<RestResponse<ReviewDTO>> updateReview(ReviewUpdateRequest updateRequest){
        return ResponseEntity.ok(RestResponse.of(reviewControllerContract.updateReview(updateRequest)));
    }

    @Operation(summary = "Delete a review", description = "Deletes a review from the database, identified by its unique ID.")
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id){
        reviewControllerContract.deleteReview(id);
    }
}
