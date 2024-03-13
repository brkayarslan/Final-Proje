package com.berkayarslan.RestaurantService.dto;

public record RestaurantUpdateRequest(String id,
                                      String name,
                                      String description,
                                      Double longitude,
                                      Double latitude) {
}
