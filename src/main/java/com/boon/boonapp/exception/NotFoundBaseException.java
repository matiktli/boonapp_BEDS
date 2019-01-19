package com.boon.boonapp.exception;

public abstract class NotFoundBaseException extends RuntimeException {

    public NotFoundBaseException(String message) {
        super(message);
    }

    public NotFoundBaseException(Long id) {
        super(id.toString());
    }
}
