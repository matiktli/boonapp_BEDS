package com.boon.boonapp.exception;

public class HelpNotFoundException extends NotFoundBaseException {

    public HelpNotFoundException(Long needyId) {
        super(String.format("Help with id [%s] not found", needyId));
    }

    public HelpNotFoundException(String msg) {
        super(String.format("%s", msg));
    }
}
