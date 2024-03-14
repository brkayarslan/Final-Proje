package com.berkayarslan.AdviceService.dto;

public record RestaurantResponseDTO(String id,
                                    String name,
                                    String description,
                                    Double latitude,
                                    Double longitude,
                                    Double distance,
                                    Integer foodScore,
                                    Integer deliveryScore,
                                    Integer presentationScore,
                                    Integer avarageScore) {
}
