package com.eventplus.domain.registration;


import com.eventplus.domain.event.Event;
import com.eventplus.domain.participant.Participant;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
import com.eventplus.domain.registration.enums.RegistrationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "registration")
@Table(name = "registration")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime registrationDate;


    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    public Registration(RegistrationCreateData data) {
        this.registrationDate = data.registration_date();
    }

}
