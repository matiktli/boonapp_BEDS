package com.boon.boonapp.exception;

public class UserNotFoundException extends NotFoundBaseException {

    public UserNotFoundException(Long userId) {
        super(String.format("User with id [%s] not found", userId));
    }

    public UserNotFoundException(String email) {
        super(String.format("User with email [%s] not found", email));
    }
}
