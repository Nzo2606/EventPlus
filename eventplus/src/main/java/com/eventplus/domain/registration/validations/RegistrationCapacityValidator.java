package com.eventplus.domain.registration.validations;

import com.eventplus.domain.event.EventRepository;
import com.eventplus.domain.registration.Registration;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// @Order(6): roda por último. Como o contador de vagas só é incrementado
// depois de todos os validadores (no RegistrationService), aqui ele ainda
// reflete as vagas ocupadas ANTES desta inscrição, o que é o comportamento correto.
@Component
@Order(6)
public class RegistrationCapacityValidator implements RegistrationCreationData{

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void validate(RegistrationCreateData data, Registration registration) throws ValidationException {
        if (data.eventId() == null){
            throw new ValidationException("Event ID must be informed!");
        }

        var event = registration.getEvent();

        if (event.getMaxParticipants() != null &&
                event.getTotalParticipants() >= event.getMaxParticipants()) {
            throw new ValidationException(
                    "You cannot register! The event reached its maximum capacity."
            );
        }
    }
}

