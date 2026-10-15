package com.eventplus.domain.event.dto;

import jakarta.validation.constraints.NotNull;

public record EventCancellationData(
                                    @NotNull
                                    Long eventId,

                                    String reason
) {
}
