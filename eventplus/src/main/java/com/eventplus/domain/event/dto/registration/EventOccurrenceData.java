package com.eventplus.domain.event.dto.registration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventOccurrenceData(
                                LocalDateTime startDate,
                                LocalDateTime endDate,
                                BigDecimal price


) {
}
