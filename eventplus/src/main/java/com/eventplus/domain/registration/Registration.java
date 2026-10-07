package com.eventplus.domain.registration;


import com.eventplus.domain.event.Event;
import com.eventplus.domain.participant.Participant;
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

    private LocalDateTime date;

    private Boolean attendance;

    private RegistrationStatus status;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
