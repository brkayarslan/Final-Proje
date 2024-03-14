package com.berkayarslan.UserService.errormessage;

import com.berkayarslan.UserService.general.BaseErrorMessage;

public enum UserErrorMessage implements BaseErrorMessage {
    INVALID_OLD_PASSWORD("Invalid old password!"),
    NEW_PASSWORD_DID_NOT_MATCH("New paswords did not match");

    private final String message;

    UserErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return null;
    }

    @Override
    public String toString() {
        return message;
    }
}
