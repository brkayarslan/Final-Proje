package com.berkayarslan.UserService.request;

import java.time.LocalDate;

public record ReviewSaveRequest(Long userId,
                                Long restaurantId,
                                Integer foodScore,
                                Integer presentationScore,
                                Integer deliveryScore,
                                String comment) {
}
