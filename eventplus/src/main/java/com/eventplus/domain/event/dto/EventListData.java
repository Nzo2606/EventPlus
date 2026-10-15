package com.eventplus.domain.event.dto;

import com.eventplus.domain.category.Category;
import com.eventplus.domain.event.Event;
import com.eventplus.domain.organizer.Organizer;
import java.time.LocalDateTime;

public record EventListData(
                            Long id,
                            String title,
                            LocalDateTime start_date,
                            LocalDateTime end_date,
                            Long organizerId,
                            Long categoryId
) {
    public EventListData(Event event) {
       this(
               event.getId(),
               event.getTitle(),
               event.getStartDate(),
               event.getEndDate(),
               event.getOrganizer().getId(),
               event.getCategory().getId()
       );
    }
}
