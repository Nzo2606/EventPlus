package com.eventplus.domain.registration.validations;

import com.eventplus.domain.event.EventRepository;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class ActiveEventValidator implements RegistrationCreationData {

    @Autowired
    EventRepository eventRepository;

    @Override
    public void validate(RegistrationCreateData data) throws ValidationException {
        var eventIsActive = eventRepository.findActiveById(data.eventId());

        if (!eventIsActive){
            throw new ValidationException("Registration could not be done by an inactive event");
        }
    }
}
