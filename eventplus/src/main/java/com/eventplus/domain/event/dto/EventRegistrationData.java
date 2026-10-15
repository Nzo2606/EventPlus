package com.eventplus.domain.event.dto;

import com.eventplus.domain.address.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventRegistrationData(
                                    @NotBlank
                                    String title,

                                    String description,

                                    @NotNull
                                    @Future
                                    LocalDateTime startDate,

                                    @NotNull
                                    @Future
                                    LocalDateTime endDate,

                                    @DecimalMin("0.00")
                                    BigDecimal price,

                                    @Positive
                                    Integer maxParticipants,

                                    @NotNull
                                    @Valid
                                    Address address,

                                    Long organizerId,

                                    @NotBlank
                                    Long categoryId
                                    ){
}
