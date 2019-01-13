package com.boon.boonapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDTO implements BaseDTO {

    @Null(message = "Token Id must NOT be present on create", groups = CreateValidationGroup.class)
    private Long id;

    @Null(message = "Token User must NOT be present on create", groups = CreateValidationGroup.class)
    private UserDTO user;

    @Null(message = "Toke Value must NOT be present on create", groups = CreateValidationGroup.class)
    private String value;

    @Null(message = "Toke Create Date must NOT be present on create", groups = CreateValidationGroup.class)
    private Timestamp createDate;

    @Null(message = "Toke Expiry Date must NOT be present on create", groups = CreateValidationGroup.class)
    private Timestamp expiryDate;

}
