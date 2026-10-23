package com.eventplus.domain.participant;

import com.eventplus.domain.event.Event;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Optional<Participant> findByNameIgnoringCase (String name);

    Optional<Participant> findBySsnIgnoringCase (String ssn);

    Boolean existsBySsnIgnoringCase (String ssn);

    @Query("""
            select p.active
            from Participant p
            where
            p.id = :id
            """)
    Boolean findActiveById(Long id);
}
