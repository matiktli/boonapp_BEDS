package com.boon.boonapp.domain;

import com.boon.boonapp.model.NeedyType;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NeedyDTO implements BaseDTO {

    @Null(message = "Needy Id must NOT be present on create", groups = CreateValidationGroup.class)
    private Long id;

    @NotNull(message = "Needy Name must be present on create/update", groups = { CreateValidationGroup.class, UpdateValidationGroup.class })
    @Size(min = 1, max = 255, message = "Needy Name must be between {min} and {max} characters", groups = { CreateValidationGroup.class, UpdateValidationGroup.class })
    private String name;

    @NotNull(message = "Needy Type must be present on create/update", groups = { CreateValidationGroup.class, UpdateValidationGroup.class })
    private NeedyType type;

    @EqualsAndHashCode.Exclude
    @NotNull(message = "Needy Location must be present on create/update", groups = { CreateValidationGroup.class, UpdateValidationGroup.class })
    //@Valid should be?
    private LocationDTO location;

    @Size(max = 500, message = "Needy Description must be no longer than {max} characters", groups = { CreateValidationGroup.class, UpdateValidationGroup.class })
    private String description;

    @Size(max = 999, message = "Needy Notes must be no longer than {max} characters", groups = { CreateValidationGroup.class, UpdateValidationGroup.class })
    private String notes;

    @EqualsAndHashCode.Exclude
    //@Size(min = 1, message = "At least one User must be assigned to Needy (like creator)", groups = CreateUpdateValidationGroup.class)
    private Set<UserDTO> attachedUsers;
}
