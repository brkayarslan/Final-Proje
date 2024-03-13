package com.berkayarslan.RestaurantService.controller;


import com.berkayarslan.RestaurantService.controller.contract.RestaurantControllerContract;
import com.berkayarslan.RestaurantService.dto.RestaurantDTO;
import com.berkayarslan.RestaurantService.dto.RestaurantSaveRequest;
import com.berkayarslan.RestaurantService.dto.RestaurantScoreDTO;
import com.berkayarslan.RestaurantService.dto.RestaurantUpdateRequest;
import com.berkayarslan.RestaurantService.model.Restaurant;
import com.berkayarslan.RestaurantService.repository.RestaurantRepository;
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
    public List<RestaurantDTO> getAllRestaurants(){
        return restaurantControllerContract.findAllRestaurants();
    }
    @GetMapping("/nearby")
    public List<RestaurantDTO> findRestaurantsWithin10Km(@RequestParam Double latitude, @RequestParam Double longitude) {
        return restaurantControllerContract.findRestaurantsWithInTenKM(latitude,longitude);
    }

    @GetMapping("/{id}")
    public RestaurantDTO findRestaurantById(@PathVariable String id){
        return restaurantControllerContract.findRestaurantById(id);
    }
    @PostMapping
    public RestaurantDTO saveRestaurant(@RequestBody RestaurantSaveRequest restaurantSaveRequest){
        return restaurantControllerContract.saveRestaurant(restaurantSaveRequest);
    }

    @PutMapping
    public RestaurantDTO updateRestaurant(@RequestBody RestaurantUpdateRequest restaurantUpdateRequest){
        return restaurantControllerContract.updateRestaurant(restaurantUpdateRequest);
    }

    @PostMapping("/score/{id}")
    public void updateRestaurantScore(@PathVariable String id, @RequestBody RestaurantScoreDTO restaurantScoreDTO){
        restaurantControllerContract.updateRestaurantScore(id,restaurantScoreDTO);
    }

    @DeleteMapping
    public void deleteRestaurant(@RequestParam String id){
        restaurantControllerContract.deleteRestaurant(id);
    }
}
