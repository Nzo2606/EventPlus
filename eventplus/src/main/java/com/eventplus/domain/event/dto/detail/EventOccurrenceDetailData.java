package com.eventplus.domain.event.dto.detail;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventOccurrenceDetailData (
                                        Long id,
                                        LocalDateTime startDate,
                                        LocalDateTime endDate,
                                        BigDecimal price
){
}
