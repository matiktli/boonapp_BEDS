package com.boon.boonapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationDTO implements BaseDTO {

    @Null(message = "Location Id must NOT be present on create", groups = CreateValidationGroup.class)
    private Long id;

    @NotNull(message = "Location Latitude must be present on create/update", groups = CreateUpdateValidationGroup.class)
    @DecimalMin(value = "-90.0000", message = "Latitude value must be no less than -90.0000", groups = CreateUpdateValidationGroup.class)
    @DecimalMax(value = "90.0000", message = "Latitude value must be no greater than 90.0000", groups = CreateUpdateValidationGroup.class)
    private Double lat;

    @NotNull(message = "Location Longitude must be present on create/update", groups = CreateUpdateValidationGroup.class)
    @DecimalMin(value = "-180.0000", message = "Longitude value must be no less than -180.0000", groups = CreateUpdateValidationGroup.class)
    @DecimalMax(value = "180.0000", message = "Longitude value must be no greater than 180.0000", groups = CreateUpdateValidationGroup.class)
    private Double lng;

    @Size(max = 255, message = "Location Name must be not longer than {max} characters", groups = CreateUpdateValidationGroup.class)
    private String name;
}
