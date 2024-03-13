package com.berkayarslan.RestaurantService.dto;

public record RestaurantDTO(String id,
                            String name,
                            String description,
                            Double latitude,
                            Double longitude,
                            Integer foodScore,
                            Integer deliveryScore,
                            Integer presentationScore,
                            Integer avarageScore,
                            Integer reviewCount) {
}
