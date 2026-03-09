package com.naveed.emsbackendv2.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateDepartmentDto(
        @NotBlank(message = "Department name cannot be empty")
        String name
) {
}