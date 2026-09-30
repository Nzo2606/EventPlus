package com.eventplus.domain.registration;

import com.eventplus.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
