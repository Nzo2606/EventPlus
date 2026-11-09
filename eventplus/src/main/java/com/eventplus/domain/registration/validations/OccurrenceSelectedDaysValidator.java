package com.eventplus.domain.registration.validations;

import com.eventplus.domain.event.EventRepository;
import com.eventplus.domain.event.ocurrence.EventOccurrenceRepository;
import com.eventplus.domain.registration.Registration;
import com.eventplus.domain.registration.RegistrationRepository;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
import com.eventplus.domain.registration.enums.RegistrationMode;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class OccurrenceSelectedDaysValidator implements RegistrationCreationData{

    @Autowired
    private EventOccurrenceRepository occurrenceRepository;

    @Override
    public void validate( RegistrationCreateData data, Registration registration) throws ValidationException {

        // 1. Pega os valores vindos do DTO
        var occurrenceIds = data.occurrenceIds();

        // 2. Verifica se o modo de registro do evento são os dias selecionados ("SELECTED_DAYS")
        if (registration.getEvent().getRegistrationMode().equals(RegistrationMode.SELECTED_DAYS)){

            // 2.1 Verifica se o campo "occurrencesIds" não está presente (null), ou está vazio (.isEmpty)
            if (occurrenceIds == null || occurrenceIds.isEmpty()) {
                throw new ValidationException(
                        "At least one occurrence must be selected."
                );
            }
        }

        // 3. Filtrando os IDs, não permitindo dados duplicados.
            //  O "HashSet" é uma implementação de "Set" que elimina elementos repetidos.
        var uniqueIds = new HashSet<>(occurrenceIds);

        // 4. Analisando
        if (uniqueIds.size() != occurrenceIds.size()) {
            throw new ValidationException(
                    "Duplicate occurrences are not allowed."
            );
        }

        var occurrences = occurrenceRepository.findAllById(occurrenceIds);

        if (occurrences.size() != uniqueIds.size()) {
            throw new ValidationException(
                    "One or more selected occurrences do not exist."
            );
        }

        boolean belongsToAnotherEvent = occurrences.stream()
                .anyMatch(occurrence ->
                        !occurrence.getEvent().getId().equals(data.eventId())
                );

        if (belongsToAnotherEvent) {
            throw new ValidationException(
                    "Selected occurrences must belong to the informed event."
            );
        }
    }

}
