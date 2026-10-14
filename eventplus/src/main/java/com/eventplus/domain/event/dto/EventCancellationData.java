package com.eventplus.domain.event.dto;

public record EventCancellationData(
                                    Long eventId,

                                    String reason
) {
}
