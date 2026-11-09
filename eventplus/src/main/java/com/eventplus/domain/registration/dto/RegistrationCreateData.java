package com.eventplus.domain.registration.dto;


import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record RegistrationCreateData(
                                    @NotNull
                                    Long participantId,

                                    @NotNull
                                    Long eventId,

                                    List<Long> occurrenceIds)
{
}
