package com.berkayarslan.RestaurantService.controller;


import com.berkayarslan.RestaurantService.controller.contract.RestaurantControllerContract;
import com.berkayarslan.RestaurantService.dto.UpdateRestaurantScoreDTO;
import com.berkayarslan.RestaurantService.dto.RestaurantDTO;
import com.berkayarslan.RestaurantService.dto.RestaurantSaveRequest;
import com.berkayarslan.RestaurantService.dto.RestaurantScoreDTO;
import com.berkayarslan.RestaurantService.dto.RestaurantUpdateRequest;
import com.berkayarslan.RestaurantService.response.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantControllerContract restaurantControllerContract;

    public RestaurantController(RestaurantControllerContract restaurantControllerContract) {
        this.restaurantControllerContract = restaurantControllerContract;
    }


    @GetMapping
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> getAllRestaurants(){
        return ResponseEntity.ok(RestResponse.of(restaurantControllerContract.findAllRestaurants()));
    }

    @GetMapping("/nearby")
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> findRestaurantsWithin10Km(@RequestParam Double latitude, @RequestParam Double longitude) {
        return ResponseEntity.ok(RestResponse.of(restaurantControllerContract.findRestaurantsWithInTenKM(latitude,longitude)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantDTO>> findRestaurantById(@PathVariable String id){
        return ResponseEntity.ok(RestResponse.of(restaurantControllerContract.findRestaurantById(id)));
    }
    @PostMapping
    public ResponseEntity<RestResponse<RestaurantDTO>> saveRestaurant(@RequestBody RestaurantSaveRequest restaurantSaveRequest){
        return ResponseEntity.ok(RestResponse.of(restaurantControllerContract.saveRestaurant(restaurantSaveRequest)));
    }

    @PutMapping
    public ResponseEntity<RestResponse<RestaurantDTO>> updateRestaurant(@RequestBody RestaurantUpdateRequest restaurantUpdateRequest){
        return ResponseEntity.ok(RestResponse.of(restaurantControllerContract.updateRestaurant(restaurantUpdateRequest)));
    }

    @PostMapping("/score/{id}")
    public ResponseEntity<RestResponse<Boolean>> saveRestaurantScore(@PathVariable String id, @RequestBody RestaurantScoreDTO restaurantScoreDTO){
        restaurantControllerContract.saveRestaurantScore(id,restaurantScoreDTO);
        return ResponseEntity.ok(RestResponse.of(true));
    }

    @PutMapping("/score/{id}")
    public ResponseEntity<RestResponse<Boolean>> updateRestaurantScore(@PathVariable String id, @RequestBody UpdateRestaurantScoreDTO updateRestaurantScoreDTO){
        restaurantControllerContract.updateRestaurantScore(id, updateRestaurantScoreDTO);
        return ResponseEntity.ok(RestResponse.of(true));
    }

    @DeleteMapping
    public void deleteRestaurant(@RequestParam String id){
        restaurantControllerContract.deleteRestaurant(id);
    }
}
