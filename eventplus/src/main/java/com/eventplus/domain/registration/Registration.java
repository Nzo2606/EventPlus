package com.eventplus.domain.registration;


import com.eventplus.domain.event.Event;
import com.eventplus.domain.participant.Participant;
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

    @Column(nullable = false)
    private LocalDateTime registrationDate;


    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    private LocalDateTime paymentConfirmedAt;



    public void setRegistrationDate() {
        this.registrationDate = LocalDateTime.now();
    }

    public void setPaymentConfirmedAt() {
        this.paymentConfirmedAt = LocalDateTime.now();
    }
}
