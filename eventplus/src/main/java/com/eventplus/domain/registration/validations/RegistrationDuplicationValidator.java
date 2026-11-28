package com.eventplus.domain.registration.validations;

import com.eventplus.domain.event.occurrence.EventOccurrence;
import com.eventplus.domain.registration.Registration;
import com.eventplus.domain.registration.RegistrationRepository;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
import com.eventplus.domain.registration.enums.RegistrationMode;
import com.eventplus.domain.registration.enums.RegistrationStatus;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// Regra: o mesmo participante pode ter várias inscrições no mesmo evento,
// desde que nenhuma ocorrência se repita entre elas.
// @Order(5): roda depois da validação de ocorrências (@Order(4)), então
// data.occurrenceIds() já foi checado (não é nulo, não tem duplicados e pertence ao evento).
@Component
@Order(5)
public class RegistrationDuplicationValidator implements RegistrationCreationData {

    // Repository responsável por buscar as inscrições já realizadas.
    @Autowired
    RegistrationRepository registrationRepository;

    @Override
    public void validate(RegistrationCreateData data, Registration registration)
            throws ValidationException {

        // Usa o ID do DTO em vez de registration.getParticipant(), porque o participante
        // só é vinculado à inscrição depois que os validadores passam.
        var participantId = data.participantId();
        var event = registration.getEvent();

        // Conjunto com os IDs das ocorrências que a NOVA inscrição quer cobrir.
        Set<Long> selectedOccurrenceIds;

        if (event.getRegistrationMode() == RegistrationMode.FULL_EVENT) {
            // Inscrição no evento inteiro: cobre todas as ocorrências do evento.
            selectedOccurrenceIds = event.getOccurrences()
                    .stream()
                    .map(EventOccurrence::getId)
                    .collect(Collectors.toSet());
        } else {
            // SELECTED_DAYS: cobre somente as ocorrências escolhidas no DTO.
            // Se a lista vier nula, o OccurrenceSelectedDaysValidator já teria
            // recusado a inscrição; aqui apenas evitamos um NullPointerException.
            if (data.occurrenceIds() == null) {
                return;
            }
            selectedOccurrenceIds = new HashSet<>(data.occurrenceIds());
        }

        // Busca somente as inscrições CONFIRMADAS do participante neste evento.
        // Inscrições canceladas não bloqueiam uma nova inscrição.
        var existingRegistrations =
                registrationRepository.findAllByParticipantAndEvent(
                        participantId, event.getId(), RegistrationStatus.CONFIRMED);

        // Caso especial: evento sem ocorrências cadastradas. Nesse caso a
        // seleção "todas as ocorrências" é vazia, e a comparação abaixo nunca
        // encontraria conflito. Assim, se já existe uma inscrição confirmada,
        // não permitimos outra.
        if (selectedOccurrenceIds.isEmpty() && !existingRegistrations.isEmpty()) {
            throw new ValidationException(
                    "You are already registered for this event!"
            );
        }

        // Compara as ocorrências da nova inscrição com as de cada inscrição anterior.
        for (var existingRegistration : existingRegistrations) {

            Set<Long> existingOccurrenceIds =
                    existingRegistration.getRegistrationOccurrences()
                            .stream()
                            .map(ro -> ro.getOccurrence().getId())
                            .collect(Collectors.toSet());

            // disjoint == true significa que não há nenhuma ocorrência em comum.
            // Basta uma ocorrência repetida para recusar a inscrição.
            if (!Collections.disjoint(existingOccurrenceIds, selectedOccurrenceIds)) {
                throw new ValidationException(
                        "One or more selected occurrences are already registered!"
                );
            }
        }
    }
}
