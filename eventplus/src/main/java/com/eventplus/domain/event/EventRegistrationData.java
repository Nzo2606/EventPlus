package com.eventplus.domain.event;

import com.eventplus.domain.category.Category;
import com.eventplus.domain.organizer.Organizer;
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
                                    LocalDateTime end_date,

                                    BigDecimal value,

                                    Organizer organizer,

                                    Category category
                                    ){
}
