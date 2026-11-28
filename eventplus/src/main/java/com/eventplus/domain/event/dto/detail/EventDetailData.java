package com.eventplus.domain.event.dto.detail;

import com.eventplus.domain.address.Address;
import com.eventplus.domain.event.Event;
import com.eventplus.domain.event.occurrence.EventOccurrence;
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
                                LocalDateTime registrationStartDate,
                                LocalDateTime registrationDeadline,
                                BigDecimal price,
                                Integer maxParticipants,
                                Address address,
                                Long organizerId,
                                Long categoryId,
                                RegistrationMode registrationMode,
                                PricingMode pricingMode,
                                // DTOs de ocorrência (e não entidades): a entidade EventOccurrence
                                // aponta de volta para o Event, o que gera JSON circular.
                                List<EventOccurrenceDetailData> occurrences

) {
    public EventDetailData(Event event) {
        this(
            event.getId(),
            event.getTitle(),
            event.getDescription(),
            event.getStartDate(),
            event.getEndDate(),
            event.getRegistrationStartDate(),
            event.getRegistrationDeadline(),
            event.getPrice(),
            event.getMaxParticipants(),
            event.getAddress(),
            event.getOrganizer().getId(),
            event.getCategory().getId(),
            event.getRegistrationMode(),
            event.getPricingMode(),
            // Converte cada entidade EventOccurrence em seu DTO (id, datas e preço).
            event.getOccurrences().stream()
                    .map(o -> new EventOccurrenceDetailData(
                            o.getId(),
                            o.getStartDateTime(),
                            o.getEndDateTime(),
                            null // preço por ocorrência não é persistido (a entidade não tem esse campo)
                    ))
                    .toList()
        );
    }
}
