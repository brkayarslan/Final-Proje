package com.berkayarslan.RestaurantService.controller.contract;

import com.berkayarslan.RestaurantService.dto.RestaurantDTO;
import com.berkayarslan.RestaurantService.dto.RestaurantSaveRequest;
import com.berkayarslan.RestaurantService.dto.RestaurantScoreDTO;
import com.berkayarslan.RestaurantService.dto.RestaurantUpdateRequest;

import java.util.List;

public interface RestaurantControllerContract {

    List<RestaurantDTO> findAllRestaurants();
    RestaurantDTO findRestaurantById(String id);
    List<RestaurantDTO> findRestaurantsWithInTenKM(Double latitude, Double longitude);
    RestaurantDTO saveRestaurant(RestaurantSaveRequest restaurantSaveRequest);
    RestaurantDTO updateRestaurant(RestaurantUpdateRequest restaurantUpdateRequest);
    void deleteRestaurant(String restaurantId);
    void updateRestaurantScore(String id, RestaurantScoreDTO restaurantScoreDTO);
}
