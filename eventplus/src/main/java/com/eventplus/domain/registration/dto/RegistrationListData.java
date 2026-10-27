package com.eventplus.domain.registration.dto;

import com.eventplus.domain.registration.Registration;
import com.eventplus.domain.registration.enums.RegistrationStatus;

import java.time.LocalDateTime;

public record RegistrationListData(
                                    Long eventId,

                                    Long participantId,

                                    LocalDateTime registration_date,

                                    RegistrationStatus status
                                    ) {
    public RegistrationListData(Registration registration) {
        this(
                registration.getEvent().getId(),
                registration.getParticipant().getId(),
                registration.getRegistration_date(),
                registration.getStatus()
        );
    }
}
