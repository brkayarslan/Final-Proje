package com.berkayarslan.RestaurantService.dto;

import org.springframework.data.solr.core.mapping.Indexed;

public record RestaurantScoreDTO(Integer foodScore,
                                 Integer deliveryScore,
                                 Integer presentationScore) {
}
