package com.boon.boonapp.exception;

public class HelpNotFoundException extends NotFoundBaseException {

    public HelpNotFoundException(Long helpId) {
        super(String.format("Help with id [%s] not found", helpId));
    }

    public HelpNotFoundException(String msg) {
        super(String.format("%s", msg));
    }
}
