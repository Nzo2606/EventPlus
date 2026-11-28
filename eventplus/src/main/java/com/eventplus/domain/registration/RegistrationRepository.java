package com.eventplus.domain.registration;

import com.eventplus.domain.registration.enums.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Query("""
    SELECT DISTINCT r
    FROM registration r
    LEFT JOIN FETCH r.registrationOccurrences ro
    LEFT JOIN FETCH ro.occurrence
    WHERE r.participant.id = :participantId
    AND r.event.id = :eventId
    AND r.status = :status
""")
//        @Query("""
//        SELECT DISTINCT r
//        FROM registration r

//            "Quero buscar as inscrições que existem no EventPlus."

//  ---

//        LEFT JOIN FETCH r.registrationOccurrences ro

//            "Para cada inscrição encontrada, quero também buscar a lista de vínculos de ocorrências que pertencem  a ela."
//

//  ---

//        LEFT JOIN FETCH ro.occurrence

//            "Além de buscar os vínculos das ocorrências, quero também carregar os objetos
//            EventOccurrence relacionados a esses vínculos."

//---

//        WHERE r.participant.id = :participantId
//            "Quero somente inscrições cujo participante tenha o ID informado."

//        AND r.event.id = :eventId
//            "Quero somente inscrições que pertençam ao evento informado."
//
    List<Registration> findAllByParticipantAndEvent(
            Long participantId,
            Long eventId,
            RegistrationStatus status
    );
}
