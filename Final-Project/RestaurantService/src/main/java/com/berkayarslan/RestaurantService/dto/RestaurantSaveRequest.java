package com.berkayarslan.RestaurantService.dto;

public record RestaurantSaveRequest(                            String name,
                                                                String description,

                                                                Double latitude,
                                                                Double longitude) {
}
