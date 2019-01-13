package com.boon.boonapp.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HelpDTO implements BaseDTO {

    @Null(message = "Help Id must NOT be present on create", groups = CreateValidationGroup.class)
    private Long id;

    @EqualsAndHashCode.Exclude
    @NotNull(message = "Help Needy must be present on create/update", groups = CreateUpdateValidationGroup.class)
    //@Valid should be?
    private NeedyDTO needy;

    @EqualsAndHashCode.Exclude
    @Size(min = 1, message = "Help Helpers must contain at least one User on create/update", groups = CreateUpdateValidationGroup.class)
    //@Valid should be?
    private Set<UserDTO> helpers;

    @Size(max = 500, message = "Help Description must be no longer than {max} characters", groups = CreateUpdateValidationGroup.class)
    private String description;

    @NotNull(message = "Help File must be present on create/update", groups = CreateUpdateValidationGroup.class)
    private FileMetadataDTO file;
}
