package com.eventplus.domain.event.dto.update;

import com.eventplus.domain.address.AddressData;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventUpdateData(
                                @NotNull
                                Long id,

                                String title,

                                String description,

                                LocalDateTime startDate,

                                LocalDateTime endDate,

                                @DecimalMin("0.00")
                                BigDecimal price,

                                @Positive
                                Integer maxParticipants,

                                AddressData address,

                                Long organizerId,

                                Long categoryId
) {

}
