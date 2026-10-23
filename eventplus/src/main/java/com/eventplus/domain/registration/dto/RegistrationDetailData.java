package com.eventplus.domain.registration.dto;

import com.eventplus.domain.registration.Registration;
import com.eventplus.domain.registration.RegistrationStatus;

import java.time.LocalDateTime;

public record RegistrationDetailData(
                                    Long id,

                                    Long eventId,

                                    Long participantId,

                                    LocalDateTime registration_date,

                                    RegistrationStatus status,

                                    Boolean attendance
) {
    public RegistrationDetailData(Registration registration) {
        this(
                registration.getId(),
                registration.getEvent().getId(),
                registration.getParticipant().getId(),
                registration.getRegistration_date(),
                registration.getStatus(),
                registration.getAttendance()
        );
    }
}
