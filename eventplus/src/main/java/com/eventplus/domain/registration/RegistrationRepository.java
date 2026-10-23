package com.eventplus.domain.registration;

import com.eventplus.domain.event.Event;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Boolean existsByParticipantIdAndDateBetween(Long id, LocalDateTime firstTimeSlot, LocalDateTime lastTimeSlot);
}
