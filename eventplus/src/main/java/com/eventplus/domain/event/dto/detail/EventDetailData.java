package com.eventplus.domain.event.dto.detail;

import com.eventplus.domain.address.Address;
import com.eventplus.domain.event.Event;
import com.eventplus.domain.event.EventOccurrence;
import com.eventplus.domain.registration.enums.PricingMode;
import com.eventplus.domain.registration.enums.RegistrationMode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record EventDetailData(
                                Long id,
                                String title,
                                String description,
                                LocalDateTime start_date,
                                LocalDateTime end_date,
                                BigDecimal price,
                                Integer maxParticipants,
                                Address address,
                                Long organizerId,
                                Long categoryId,
                                RegistrationMode registrationMode,
                                PricingMode pricingMode,
                                List<EventOccurrence> occurrences

) {
    public EventDetailData(Event event) {
        this(
            event.getId(),
            event.getTitle(),
            event.getDescription(),
            event.getStartDate(),
            event.getEndDate(),
            event.getPrice(),
            event.getMaxParticipants(),
            event.getAddress(),
            event.getOrganizer().getId(),
            event.getCategory().getId(),
            event.getRegistrationMode(),
            event.getPricingMode(),
            event.getOccurrences()
        );
    }
}
