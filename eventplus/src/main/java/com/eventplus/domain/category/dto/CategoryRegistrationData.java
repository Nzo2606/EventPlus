package com.eventplus.domain.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRegistrationData(
                                        @NotBlank
                                        String name
) {
}
