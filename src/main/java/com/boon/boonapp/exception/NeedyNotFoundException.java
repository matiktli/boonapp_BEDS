package com.boon.boonapp.exception;

public class NeedyNotFoundException extends NotFoundBaseException {


    public NeedyNotFoundException(Long needyId) {
        super(String.format("Needy with id [%s] not found", needyId));
    }

    public NeedyNotFoundException(String msg) {
        super(String.format("%s", msg));
    }
}
