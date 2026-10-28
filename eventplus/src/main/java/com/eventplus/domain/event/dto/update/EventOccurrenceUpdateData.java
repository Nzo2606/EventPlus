package com.eventplus.domain.event.dto.update;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventOccurrenceUpdateData(
                                        Long id,
                                        LocalDateTime startDate,
                                        LocalDateTime endDate,
                                        BigDecimal price
) {
}
