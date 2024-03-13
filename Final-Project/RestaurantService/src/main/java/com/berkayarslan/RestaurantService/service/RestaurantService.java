package com.berkayarslan.RestaurantService.service;

import com.berkayarslan.RestaurantService.model.Restaurant;
import com.berkayarslan.RestaurantService.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Iterable<Restaurant> findAllRestaurants(){
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> findRestaurantById(String id){
        return restaurantRepository.findById(id);
    }

    public Restaurant findByIdWithControl(String id){
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        Restaurant restaurant;

        if(optionalRestaurant.isPresent()){
            restaurant = optionalRestaurant.get();
        }else {
            throw new RuntimeException();
        }
        return restaurant;
    }
    public List<Restaurant> findRestaurantsWithInTenKM(Double latitude, Double longitude){
        List<Restaurant> restaurantsWithInTenKm = restaurantRepository.findRestaurantsWithInTenKm(latitude.toString(), longitude.toString());
        return restaurantsWithInTenKm;
    }

    public Restaurant saveRestaurant(Restaurant restaurant){
        restaurant.setFoodScore(0);
        restaurant.setDeliveryScore(0);
        restaurant.setPresentationScore(0);
        restaurant.setAvarageScore(0);
        restaurant.setReviewCount(0);
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurantById(String id){
        restaurantRepository.deleteById(id);
    }
}
