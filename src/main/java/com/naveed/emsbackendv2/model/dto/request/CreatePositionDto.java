package com.naveed.emsbackendv2.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreatePositionDto(
        @NotBlank(message = "Position name cannot be empty")
        String name
) {
}