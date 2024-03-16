package com.berkayarslan.UserService.dto;

public record ReviewUpdateScoreDTO(Integer oldFoodScore,
                                   Integer oldPresentationScore,
                                   Integer oldDeliveryScore,
                                   Integer newFoodScore,
                                   Integer newPresentationScore,
                                   Integer newDeliveryScore) {
}
