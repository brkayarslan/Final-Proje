package com.berkayarslan.RestaurantService.controller;


import com.berkayarslan.RestaurantService.controller.contract.RestaurantControllerContract;
import com.berkayarslan.RestaurantService.dto.UpdateRestaurantScoreDTO;
import com.berkayarslan.RestaurantService.dto.RestaurantDTO;
import com.berkayarslan.RestaurantService.dto.RestaurantSaveRequest;
import com.berkayarslan.RestaurantService.dto.RestaurantScoreDTO;
import com.berkayarslan.RestaurantService.dto.RestaurantUpdateRequest;
import com.berkayarslan.RestaurantService.response.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@Tag(name = "Restaurant Controller", description = "Provides endpoints for managing restaurants, including operations for retrieving, creating, updating, and deleting restaurant information.")
public class RestaurantController {

    private final RestaurantControllerContract restaurantControllerContract;

    public RestaurantController(RestaurantControllerContract restaurantControllerContract) {
        this.restaurantControllerContract = restaurantControllerContract;
    }

    @Operation(summary = "Get all restaurants", description = "Retrieves all active restaurants")
    @GetMapping
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> getAllRestaurants(){
        return ResponseEntity.ok(RestResponse.of(restaurantControllerContract.findAllRestaurants()));
    }

    @Operation(summary = "Get restaurants within 10 km of the user.", description = "It brings up restaurants within 10 km of the user.")
    @GetMapping("/nearby")
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> findRestaurantsWithin10Km(@RequestParam Double latitude, @RequestParam Double longitude) {
        return ResponseEntity.ok(RestResponse.of(restaurantControllerContract.findRestaurantsWithInTenKM(latitude,longitude)));
    }

    @Operation(summary = "Get the restaurant by restaurant ID.", description = "Returns the restaurant according to the restaurant ID.")
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantDTO>> findRestaurantById(@PathVariable String id){
        return ResponseEntity.ok(RestResponse.of(restaurantControllerContract.findRestaurantById(id)));
    }

    @Operation(
            description = "Creates new restaurant",
            summary = "Create",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurants",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = RestaurantSaveRequest.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new restaurant",
                                                    summary = "Default",
                                                    description = "Complete request with all available fields for restaurant",
                                                    value = "{\n"
                                                            + "  \"name\": \"Mc Donald's\",\n"
                                                            + "  \"description\": \"Burger\",\n"
                                                            + "  \"latitude\": 41.02208020801947,\n"
                                                            + "  \"longitude\": 28.999552954557206\n"
                                                            + "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PostMapping
    public ResponseEntity<RestResponse<RestaurantDTO>> saveRestaurant(@RequestBody RestaurantSaveRequest restaurantSaveRequest){
        return ResponseEntity.ok(RestResponse.of(restaurantControllerContract.saveRestaurant(restaurantSaveRequest)));
    }

    @Operation(
            description = "Updates old restaurant",
            summary = "Update restaurant",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurants",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = RestaurantUpdateRequest.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "update restaurant",
                                                    summary = "Default",
                                                    description = "Complete request with all available fields for restaurant",
                                                    value = "{\n"
                                                            + "  \"id\": \"40\",\n"
                                                            + "  \"name\": \"Mc Donald's\",\n"
                                                            + "  \"description\": \"Burger\",\n"
                                                            + "  \"latitude\": 41.0220802080194,\n"
                                                            + "  \"longitude\": 28.999552954557206\n"
                                                            + "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PutMapping
    public ResponseEntity<RestResponse<RestaurantDTO>> updateRestaurant(@RequestBody RestaurantUpdateRequest restaurantUpdateRequest){
        return ResponseEntity.ok(RestResponse.of(restaurantControllerContract.updateRestaurant(restaurantUpdateRequest)));
    }


    @Operation(
            description = "Save restaurant reviews scores",
            summary = "Save",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurants",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = RestaurantSaveRequest.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new reviews scores",
                                                    summary = "Default",
                                                    description = "Complete request with all available fields for restaurant",
                                                    value = "{\n"
                                                            + "  \"foodScore\": 5,\n"
                                                            + "  \"deliveryScore\": 4,\n"
                                                            + "  \"presentationScore\": 5\n"
                                                            + "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PostMapping("/score/{id}")
    public ResponseEntity<RestResponse<Boolean>> saveRestaurantScore(@PathVariable String id, @RequestBody RestaurantScoreDTO restaurantScoreDTO){
        restaurantControllerContract.saveRestaurantScore(id,restaurantScoreDTO);
        return ResponseEntity.ok(RestResponse.of(true));
    }


    @Operation(
            description = "Save restaurant reviews scores",
            summary = "Save",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurants",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = RestaurantSaveRequest.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new reviews scores",
                                                    summary = "Default",
                                                    description = "Complete request with all available fields for restaurant",
                                                    value = "{\n"
                                                            + "  \"oldFoodScore\": 5,\n"
                                                            + "  \"oldDeliveryScore\": 4,\n"
                                                            + "  \"oldPresentationScore\": 5\n"
                                                            + "  \"newFoodScore\": 5,\n"
                                                            + "  \"newDeliveryScore\": 4,\n"
                                                            + "  \"newPresentationScore\": 5\n"
                                                            + "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PutMapping("/score/{id}")
    public ResponseEntity<RestResponse<Boolean>> updateRestaurantScore(@PathVariable String id, @RequestBody UpdateRestaurantScoreDTO updateRestaurantScoreDTO){
        restaurantControllerContract.updateRestaurantScore(id, updateRestaurantScoreDTO);
        return ResponseEntity.ok(RestResponse.of(true));
    }

    @Operation(summary = "Delete restaurant by restaurant id")
    @DeleteMapping
    public void deleteRestaurant(@RequestParam String id){
        restaurantControllerContract.deleteRestaurant(id);
    }
}
