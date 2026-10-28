package com.eventplus.domain.event.dto.registration;

import com.eventplus.domain.address.AddressData;
import com.eventplus.domain.registration.enums.PricingMode;
import com.eventplus.domain.registration.enums.RegistrationMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
                                    AddressData address,

                                    Long organizerId,

                                    @NotNull
                                    Long categoryId,

                                    RegistrationMode registrationMode,

                                    PricingMode pricingMode,

                                    List<@Valid EventOccurrenceData> occurrences
                                    ){
}
