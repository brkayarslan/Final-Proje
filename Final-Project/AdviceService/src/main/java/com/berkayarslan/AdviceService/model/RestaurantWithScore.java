package com.berkayarslan.AdviceService.model;

import com.berkayarslan.AdviceService.dto.RestaurantInfoDTO;

public class RestaurantWithScore {

    private final RestaurantInfoDTO restaurant;
    private final double score;

    private final double distance;

    public RestaurantWithScore(RestaurantInfoDTO restaurant, double score, double distance) {
        this.restaurant = restaurant;
        this.score = score;
        this.distance = distance;
    }

    public RestaurantInfoDTO getRestaurant() {
        return restaurant;
    }

    public double getScore() {
        return score;
    }

    public double getDistance() {
        return distance;
    }
}
