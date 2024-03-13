package com.berkayarslan.RestaurantService.mapper;


import com.berkayarslan.RestaurantService.dto.RestaurantDTO;
import com.berkayarslan.RestaurantService.dto.RestaurantSaveRequest;
import com.berkayarslan.RestaurantService.dto.RestaurantUpdateRequest;
import com.berkayarslan.RestaurantService.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class RestaurantMapper {

    public RestaurantDTO convertRestaurantToRestaurantDTO(Restaurant restaurant){
        return new RestaurantDTO(restaurant.getId(),
                restaurant.getName(),
                restaurant.getDescription(),
                restaurant.getLatitude(),
                restaurant.getLongitude(),
                restaurant.getFoodScore(),
                restaurant.getDeliveryScore(),
                restaurant.getPresentationScore(),
                restaurant.getAvarageScore(),
                restaurant.getReviewCount());
    }

    public List<RestaurantDTO> convertToDTOList(Iterable<Restaurant> restaurants){
        return StreamSupport.stream(restaurants.spliterator(), false)
                .map(this::convertRestaurantToRestaurantDTO)
                .collect(Collectors.toList());
    }

    public List<RestaurantDTO> convertToDTOList(List<Restaurant> restaurants){
        return restaurants.stream().map(this::convertRestaurantToRestaurantDTO).collect(Collectors.toList());
    }
//    public Restaurant convertRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO){
//        return new Restaurant(restaurantDTO.id(),
//                restaurantDTO.name(),
//                restaurantDTO.description(),
//                restaurantDTO.latitude(),
//                restaurantDTO.longitude(),
//                restaurantDTO.foodScore(),
//                restaurantDTO.deliveryScore(),
//                restaurantDTO.presentationScore(),
//                restaurantDTO.avarageScore());
//    }

    public Restaurant convertSaveRequestToRestaurant(RestaurantSaveRequest restaurantSaveRequest){
        return new Restaurant(restaurantSaveRequest.name(),
                restaurantSaveRequest.description(),
                restaurantSaveRequest.latitude(),
                restaurantSaveRequest.longitude());
    }

    public Restaurant convertUpdateRequestToRestaurant(Restaurant restaurant, RestaurantUpdateRequest restaurantUpdateRequest){
        if(restaurantUpdateRequest == null){
            return null;
        }
        restaurant.setName(restaurantUpdateRequest.name());
        restaurant.setDescription(restaurantUpdateRequest.description());
        restaurant.setLongitude(restaurantUpdateRequest.longitude());
        restaurant.setLatitude(restaurantUpdateRequest.latitude());
        return restaurant;
    }
}
