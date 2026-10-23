package com.eventplus.domain.registration;


import com.eventplus.domain.event.Event;
import com.eventplus.domain.participant.Participant;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
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

    private LocalDateTime registration_date;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean attendance;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Registration(RegistrationCreateData data) {
        this.registration_date = data.registration_date();
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }
}
