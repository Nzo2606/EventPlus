package com.eventplus.domain.event.dto;

import com.eventplus.domain.address.Address;
import com.eventplus.domain.category.Category;
import com.eventplus.domain.organizer.Organizer;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventRegistrationData(
                                    @NotBlank
                                    String title,

                                    String description,

                                    @NotNull
                                    LocalDateTime start_date,

                                    @NotNull
                                    @Future
                                    LocalDateTime end_date,

                                    BigDecimal price,

                                    Integer maxParticipants,

                                    @NotNull
                                    Address address,

                                    Organizer organizer,

                                    @NotBlank
                                    Category category
                                    ){
}
