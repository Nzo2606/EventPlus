package com.eventplus.domain.registration;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Boolean existsByParticipantIdAndRegistrationDateBetween(Long id, LocalDateTime firstTimeSlot, LocalDateTime lastTimeSlot);
}
