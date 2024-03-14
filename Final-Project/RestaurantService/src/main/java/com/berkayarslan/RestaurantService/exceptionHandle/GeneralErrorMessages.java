package com.berkayarslan.RestaurantService.exceptionHandle;

import java.time.LocalDateTime;

public record GeneralErrorMessages(LocalDateTime date,
                                   String message,
                                   String description) {
}
