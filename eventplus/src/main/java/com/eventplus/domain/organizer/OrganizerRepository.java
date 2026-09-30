package com.eventplus.domain.organizer;

import com.eventplus.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
}
