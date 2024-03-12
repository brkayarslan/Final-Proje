package com.berkayarslan.UserService.request;

public record ReviewUpdateRequest(Long id,
                                  Integer foodScore,
                                  Integer presentationScore,
                                  Integer deliveryScore,
                                  String comment) {
}
