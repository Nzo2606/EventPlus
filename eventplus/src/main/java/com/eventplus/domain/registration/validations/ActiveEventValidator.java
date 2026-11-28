package com.eventplus.domain.registration.validations;

import com.eventplus.domain.event.EventRepository;
import com.eventplus.domain.registration.Registration;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// @Order(1): primeira verificação, barata (uma consulta simples).
@Component
@Order(1)
public class ActiveEventValidator implements RegistrationCreationData {

    @Autowired
    EventRepository eventRepository;

    @Override
    public void validate(RegistrationCreateData data, Registration registration) throws ValidationException {
        // findActiveById retorna Boolean (objeto), que pode ser null.
        // Boolean.TRUE.equals(...) trata null como "não ativo" sem gerar NullPointerException.
        var eventIsActive = Boolean.TRUE.equals(eventRepository.findActiveById(data.eventId()));

        if (!eventIsActive){
            throw new ValidationException("Registration could not be done by an inactive event");
        }
    }
}
