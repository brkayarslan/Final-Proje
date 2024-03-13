package com.berkayarslan.UserService.dto;

import com.berkayarslan.UserService.model.User;

import java.time.LocalDate;

public record ReviewDTO(Long id,
                        String userName,
                        String restaurantId,
                        Integer foodScore,
                        Integer presentationScore,
                        Integer deliveryScore,
                        String comment,
                        LocalDate reviewDate) {
}
