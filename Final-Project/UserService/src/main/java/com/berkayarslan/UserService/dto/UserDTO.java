package com.berkayarslan.UserService.dto;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String telephone,
        String eMail,
        String password,
        Double latitude,
        Double longitude) {
}
