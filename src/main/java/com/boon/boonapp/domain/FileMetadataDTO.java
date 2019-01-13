package com.boon.boonapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileMetadataDTO implements BaseDTO {

    @Null(message = "File Id must NOT be present on create", groups = CreateValidationGroup.class)
    private Long id;

    @NotNull(message = "File Name must be present on create/update", groups = CreateUpdateValidationGroup.class)
    @Size(min = 1, max = 500, message = "File Name must be between {min} and {max} characters", groups = CreateUpdateValidationGroup.class)
    private String name;

    @NotNull(message = "File Path must be present on create/update", groups = CreateUpdateValidationGroup.class)
    @Size(min = 1, max = 999, message = "File Path must be between {min} and {max} characters", groups = CreateUpdateValidationGroup.class)
    private String path;

    @Null(message = "File Active must NOT be present on create", groups = CreateValidationGroup.class)
    private Boolean active;
}
