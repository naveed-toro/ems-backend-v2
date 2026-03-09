package com.naveed.emsbackendv2.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateDepartmentDto(
        @NotBlank(message = "Department name cannot be empty")
        String name
) {
}