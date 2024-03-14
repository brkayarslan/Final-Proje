package com.berkayarslan.AdviceService.dto;

public record RestaurantInfoDTO(String id,
                                String name,
                                String description,
                                Double latitude,
                                Double longitude,
                                Integer foodScore,
                                Integer deliveryScore,
                                Integer presentationScore,
                                Integer avarageScore) {
}
