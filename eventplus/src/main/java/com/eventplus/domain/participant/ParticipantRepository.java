package com.eventplus.domain.participant;

import com.eventplus.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Optional<Participant> findByNameIgnoringCase (String name);

    Optional<Participant> findBySsnIgnoringCase (String ssn);

    Boolean existsBySsnIgnoringCase (String ssn);

}
