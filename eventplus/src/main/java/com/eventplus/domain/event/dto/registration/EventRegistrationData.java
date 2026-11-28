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

                                    // Sem @Future: a inscrição pode abrir agora. Com @Future, a data de início
                                    // precisaria estar no futuro, e o validador de período (agora dentro da
                                    // janela) nunca aceitaria uma inscrição.
                                    @NotNull
                                    LocalDateTime registrationStartDate,

                                    @NotNull
                                    @Future
                                    LocalDateTime registrationDeadline,

                                    @DecimalMin("0.00")
                                    BigDecimal price,

                                    @Positive
                                    Integer maxParticipants,

                                    @NotNull
                                    @Valid
                                    AddressData address,

                                    @NotNull
                                    Long organizerId,

                                    @NotNull
                                    Long categoryId,

                                    // Obrigatórios: são colunas NOT NULL no banco (V9).
                                    @NotNull
                                    RegistrationMode registrationMode,

                                    @NotNull
                                    PricingMode pricingMode,

                                    List<@Valid EventOccurrenceData> occurrences
                                    ){
}
