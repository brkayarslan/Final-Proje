package com.berkayarslan.UserService.request;

public record UserSaveRequest(String firstName,
                              String lastName,
                              String telephone,
                              String e_mail,
                              String password,
                              Double latitude,
                              Double longitude) {
}
