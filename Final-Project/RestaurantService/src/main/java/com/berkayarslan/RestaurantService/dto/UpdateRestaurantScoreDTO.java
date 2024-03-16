package com.berkayarslan.RestaurantService.dto;

public record UpdateRestaurantScoreDTO(Integer oldFoodScore,
                                       Integer oldDeliveryScore,
                                       Integer oldPresentationScore,
                                       Integer newFoodScore,
                                       Integer newDeliveryScore,
                                       Integer newPresentationScore) {
}
