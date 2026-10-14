package com.eventplus.domain.event.dto;

import com.eventplus.domain.address.Address;
import com.eventplus.domain.category.Category;
import com.eventplus.domain.event.Event;
import com.eventplus.domain.organizer.Organizer;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventDetailData(
                                Long id,
                                String title,
                                String description,
                                LocalDateTime start_date,
                                LocalDateTime end_date,
                                BigDecimal price,
                                Integer maxParticipants,
                                Address address,
                                Organizer organizer,
                                Category category

) {
    public EventDetailData(Event event) {
        this(
            event.getId(),
            event.getTitle(),
            event.getDescription(),
            event.getStart_date(),
            event.getEnd_date(),
            event.getPrice(),
            event.getMaxParticipants(),
            event.getAddress(),
            event.getOrganizer(),
            event.getCategory()
        );
    }
}
