package com.boon.boonapp.exception;

public class TokenNotFoundException extends NotFoundBaseException {

    public TokenNotFoundException(Long userId) {
        super(String.format("There were no tokens for user with id: [%s]", userId));
    }

    public TokenNotFoundException(String token) {
        super(String.format("Token with value [%s] not found", token));
    }

    public TokenNotFoundException(Long userId, String msg) {
        super(String.format("%s. There were no tokens for user with id: [%s]", msg, userId));
    }
}
