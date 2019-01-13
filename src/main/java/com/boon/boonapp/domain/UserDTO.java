package com.boon.boonapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO implements BaseDTO {

    @Null(message = "User Id must NOT be present on create", groups = CreateValidationGroup.class)
    private Long id;

    @Null(message = "User Email must NOT be present on update", groups = UpdateValidationGroup.class)
    @NotNull(message = "User Email must be present on create", groups = CreateValidationGroup.class)
    @Email(message = "User Email must be valid email address", groups = CreateValidationGroup.class)
    @Size(max = 255, message = "User Email must be no longer than {max} characters", groups = CreateValidationGroup.class)
    private String email;

    @NotNull(message = "User First Name must be present on create/update", groups = CreateUpdateValidationGroup.class)
    @Size(min = 1, max = 255, message = "User First Name must be between {min} and {max} characters", groups = CreateUpdateValidationGroup.class)
    private String firstName;

    @NotNull(message = "User Last Name must be present on create/update", groups = CreateUpdateValidationGroup.class)
    @Size(min = 1, max = 255, message = "User Last Name must be between {min} and {max} characters", groups = CreateUpdateValidationGroup.class)
    private String lastName;

    @JsonIgnore
    @NotNull(message = "User Password must be present on create", groups = { CreateValidationGroup.class})
    @Null(message = "User Password must NOT be present on update", groups = { UpdateValidationGroup.class})
    @Size(min = 1, max = 255, message = "User Password must be between {min} and {max} characters", groups = CreateValidationGroup.class)
    private String password;

    @EqualsAndHashCode.Exclude
    @NotNull(message = "User Location must be present on create/update", groups = CreateUpdateValidationGroup.class)
    //@Valid should be?
    private LocationDTO location;
}
