package com.naveed.emsbackendv2.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreatePositionDto(
        @NotBlank(message = "Position name cannot be empty")
        String name
) {
}