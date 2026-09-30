package com.eventplus.domain.participant;

import com.eventplus.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
