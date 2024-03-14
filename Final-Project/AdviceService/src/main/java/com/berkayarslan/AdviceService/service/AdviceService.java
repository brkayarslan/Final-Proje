package com.berkayarslan.AdviceService.service;

import com.berkayarslan.AdviceService.Response.RestResponse;
import com.berkayarslan.AdviceService.client.RestaurantClient;
import com.berkayarslan.AdviceService.client.UserClient;
import com.berkayarslan.AdviceService.dto.RestaurantInfoDTO;
import com.berkayarslan.AdviceService.dto.RestaurantResponseDTO;
import com.berkayarslan.AdviceService.dto.UserLocationDTO;
import com.berkayarslan.AdviceService.model.RestaurantWithScore;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdviceService {

    private final RestaurantClient restaurantClient;
    private final UserClient userClient;
    private static final int EARTH_RADIUS_KM = 6371;
    public AdviceService(RestaurantClient restaurantClient, UserClient userClient) {
        this.restaurantClient = restaurantClient;
        this.userClient = userClient;
    }

    public List<RestaurantInfoDTO> getRestaurants(){
        List<RestaurantInfoDTO> restaurants = restaurantClient.getRestaurants();
        return restaurants;
    }

    public List<RestaurantInfoDTO> getNearbyRestaurants(Double latitude, Double longitude){
        return restaurantClient.getNearbyRestaurants(latitude,longitude);
    }
    public UserLocationDTO getUserLocationById(Long id){
        ResponseEntity<RestResponse<UserLocationDTO>> user = userClient.getUserLocationById(id);
        return new UserLocationDTO(user.getBody().getData().latitude(),
                user.getBody().getData().longitude());
    }

    public double haversineCalculator(Double userLat, Double userLong, Double restaurantLat, Double restaurantLong ){
        // Enlem ve boylamı radyan cinsine dönüştür
        double dLat = Math.toRadians(userLat - restaurantLat);
        double dLong = Math.toRadians(userLong - restaurantLong);

        userLat = Math.toRadians(userLat);
        restaurantLat = Math.toRadians(restaurantLat);

        // Haversine formülü
        double a = Math.pow(Math.sin(dLat/2),2)
                 + Math.pow(Math.sin(dLong/2),2)
                 * Math.cos(userLat) * Math.cos(restaurantLat);
        double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }

    public List<RestaurantResponseDTO> getAdviceRestaurantsByUserId(Long id){

        UserLocationDTO userLocation = getUserLocationById(id);


        List<RestaurantInfoDTO> nearbyRestaurants = getNearbyRestaurants(userLocation.latitude(), userLocation.longitude());

        // Her restaurant için skor hesapla ve RestaurantWithScore listesi oluştur
        List<RestaurantWithScore> scoredRestaurants = nearbyRestaurants.stream().map(restaurant -> {
            double distance = haversineCalculator(userLocation.latitude(), userLocation.longitude(), restaurant.latitude(), restaurant.longitude());
            double score = ((10 - distance) * 3) + (((double) restaurant.avarageScore() / 50) * 7);
            return new RestaurantWithScore(restaurant, score,distance);
        }).collect(Collectors.toList());
        // Skora göre sırala ve ilk 3'ü seç
        List<RestaurantResponseDTO> advisedRestaurants = scoredRestaurants.stream()
                .sorted(Comparator.comparingDouble(RestaurantWithScore::getScore).reversed()) // Skora göre azalan sırada sırala
                .limit(3)
                .map(rws -> new RestaurantResponseDTO(
                        rws.getRestaurant().id(),
                        rws.getRestaurant().name(),
                        rws.getRestaurant().description(),
                        rws.getRestaurant().latitude(),
                        rws.getRestaurant().longitude(),
                        rws.getDistance(),
                        rws.getRestaurant().foodScore(),
                        rws.getRestaurant().deliveryScore(),
                        rws.getRestaurant().presentationScore(),
                        rws.getRestaurant().avarageScore()
                )) // RestaurantWithScore nesnelerinden RestaurantResponseDTO'ları oluştur
                .collect(Collectors.toList());
        return advisedRestaurants;
    }

}
