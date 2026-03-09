package com.naveed.emsbackendv2.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateEmployeeDto(
        @NotBlank(message = "Employee name cannot be empty")
        String name,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Department UUID is required")
        String departmentUuid,

        @NotBlank(message = "Position UUID is required")
        String positionUuid
) {
}