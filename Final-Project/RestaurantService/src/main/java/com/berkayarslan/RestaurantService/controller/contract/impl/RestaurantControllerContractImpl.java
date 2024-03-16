package com.berkayarslan.RestaurantService.controller.contract.impl;

import com.berkayarslan.RestaurantService.controller.contract.RestaurantControllerContract;
import com.berkayarslan.RestaurantService.dto.*;
import com.berkayarslan.RestaurantService.mapper.RestaurantMapper;
import com.berkayarslan.RestaurantService.model.Restaurant;
import com.berkayarslan.RestaurantService.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantControllerContractImpl implements RestaurantControllerContract {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    public RestaurantControllerContractImpl(RestaurantService restaurantService, RestaurantMapper restaurantMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public List<RestaurantDTO> findAllRestaurants() {
        Iterable<Restaurant> allRestaurants = restaurantService.findAllRestaurants();
        return restaurantMapper.convertToDTOList(allRestaurants);
    }

    @Override
    public RestaurantDTO findRestaurantById(String id) {
        Restaurant byIdWithControl = restaurantService.findByIdWithControl(id);
        return restaurantMapper.convertRestaurantToRestaurantDTO(byIdWithControl);
    }

    @Override
    public List<RestaurantDTO> findRestaurantsWithInTenKM(Double latitude, Double longitude) {
        List<Restaurant> restaurantsWithInTenKM = restaurantService.findRestaurantsWithInTenKM(latitude, longitude);
        return restaurantMapper.convertToDTOList(restaurantsWithInTenKM);
    }

    @Override
    public RestaurantDTO saveRestaurant(RestaurantSaveRequest restaurantSaveRequest) {
        Restaurant restaurant = restaurantMapper.convertSaveRequestToRestaurant(restaurantSaveRequest);
        restaurant = restaurantService.firestSaveOfRestaurant(restaurant);
        return restaurantMapper.convertRestaurantToRestaurantDTO(restaurant);
    }

    @Override
    public RestaurantDTO updateRestaurant(RestaurantUpdateRequest restaurantUpdateRequest) {
        Restaurant restaurant = restaurantService.findByIdWithControl(restaurantUpdateRequest.id());
        restaurant = restaurantMapper.convertUpdateRequestToRestaurant(restaurant,restaurantUpdateRequest);
        restaurant = restaurantService.saveRestaurant(restaurant);
        return restaurantMapper.convertRestaurantToRestaurantDTO(restaurant);
    }

    @Override
    public void saveRestaurantScore(String id, RestaurantScoreDTO restaurantScoreDTO) {

        Restaurant restaurant = restaurantService.findByIdWithControl(id);
        Integer reviewCount = restaurant.getReviewCount();

        if (restaurant.getAvarageScore() == 0){
            Integer foodScore = restaurantScoreDTO.foodScore() * 100;
            Integer presentationScore = restaurantScoreDTO.presentationScore() * 100;
            Integer deliveryScore = restaurantScoreDTO.deliveryScore() * 100;
            Double avarageScore = (double) ((foodScore + presentationScore + deliveryScore) / 3);

            restaurant.setFoodScore(foodScore);
            restaurant.setPresentationScore(presentationScore);
            restaurant.setDeliveryScore(deliveryScore);
            restaurant.setAvarageScore(avarageScore.intValue());
        }
        else{
            Double foodScore = (double) ((reviewCount * restaurant.getFoodScore() + (restaurantScoreDTO.foodScore() * 100)) / (reviewCount + 1));
            Double presentationScore = (double) ((reviewCount * restaurant.getPresentationScore() + (restaurantScoreDTO.presentationScore() * 100)) / (reviewCount + 1));
            Double deliveryScore = (double) ((reviewCount * restaurant.getDeliveryScore() + (restaurantScoreDTO.deliveryScore() * 100)) / (reviewCount + 1));
            Double avarageScore = (foodScore + presentationScore + deliveryScore) / 3;

            restaurant.setFoodScore(foodScore.intValue());
            restaurant.setPresentationScore(presentationScore.intValue());
            restaurant.setDeliveryScore(deliveryScore.intValue());
            restaurant.setAvarageScore(avarageScore.intValue());
        }
        restaurant.setReviewCount(restaurant.getReviewCount()+1);

        restaurantService.saveRestaurant(restaurant);
    }

    public void updateRestaurantScore(String id, UpdateRestaurantScoreDTO updateRestaurantScoreDTO){

        Restaurant restaurant = restaurantService.findByIdWithControl(id);
        Integer reviewCount = restaurant.getReviewCount();

        Integer reviewFoodScore = updateRestaurantScoreDTO.newFoodScore() - updateRestaurantScoreDTO.oldFoodScore();
        Integer reviewPresentationScore = updateRestaurantScoreDTO.newPresentationScore() - updateRestaurantScoreDTO.oldPresentationScore();
        Integer reviewDeliveryScore = updateRestaurantScoreDTO.newDeliveryScore() - updateRestaurantScoreDTO.oldDeliveryScore();

        Double foodScore = (double) ((reviewCount * restaurant.getFoodScore() + (reviewFoodScore * 100)) / (reviewCount));
        Double presentationScore = (double) ((reviewCount * restaurant.getPresentationScore() + (reviewPresentationScore * 100)) / (reviewCount));
        Double deliveryScore = (double) ((reviewCount * restaurant.getDeliveryScore() + (reviewDeliveryScore * 100)) / (reviewCount));
        Double avarageScore = (foodScore + presentationScore + deliveryScore) / 3;

        restaurant.setFoodScore(foodScore.intValue());
        restaurant.setPresentationScore(presentationScore.intValue());
        restaurant.setDeliveryScore(deliveryScore.intValue());
        restaurant.setAvarageScore(avarageScore.intValue());

        restaurantService.saveRestaurant(restaurant);
    }

    @Override
    public void deleteRestaurant(String restaurantId) {
        restaurantService.deleteRestaurantById(restaurantId);
    }


}
