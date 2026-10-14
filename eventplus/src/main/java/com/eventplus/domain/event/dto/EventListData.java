package com.eventplus.domain.event.dto;

import com.eventplus.domain.category.Category;
import com.eventplus.domain.event.Event;
import com.eventplus.domain.organizer.Organizer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventListData(
                            Long id,
                            String title,
                            LocalDateTime start_date,
                            LocalDateTime end_date,
                            Organizer organizer,
                            Category category
) {
    public EventListData(Event event) {
       this(
               event.getId(),
               event.getTitle(),
               event.getStart_date(),
               event.getEnd_date(),
               event.getOrganizer(),
               event.getCategory()
       );
    }
}
