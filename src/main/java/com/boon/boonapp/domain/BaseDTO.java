package com.boon.boonapp.domain;

import javax.validation.GroupSequence;

public interface BaseDTO {

    public interface CreateValidationGroup { }

    public interface UpdateValidationGroup { }

    @GroupSequence({CreateValidationGroup.class, UpdateValidationGroup.class})
    public interface CreateUpdateValidationGroup { }
}
