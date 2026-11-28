package com.eventplus.domain.registration.occurrence;

import com.eventplus.domain.event.occurrence.EventOccurrence;
import com.eventplus.domain.registration.Registration;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "registration_occurrence")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class RegistrationOccurrence {

    // 'RegistrationOccurrence' é a classe que registra quais dias de um evento cada inscrição inclui,
    //  permitindo também controlar a presença do participante em cada um desses dias.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "registration_id", nullable = false)
    private Registration registration;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "occurrence_id", nullable = false)
    private EventOccurrence occurrence;

    // TINYINT (e não BIT) para bater com o tipo da coluna criada na V9 e com o resto do projeto.
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean attendance = false;
}
