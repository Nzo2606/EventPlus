package com.eventplus.domain.registration.dto;


import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegistrationCreateData(
                                    @NotNull
                                    Long participantId,

                                    @NotNull
                                    Long eventId,

                                    LocalDateTime registration_date
) {
}
