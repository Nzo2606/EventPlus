package com.eventplus.domain.event.dto.cancellation;

import jakarta.validation.constraints.NotNull;

public record EventCancellationData(
                                    @NotNull
                                    Long eventId,

                                    String reason
) {
}
