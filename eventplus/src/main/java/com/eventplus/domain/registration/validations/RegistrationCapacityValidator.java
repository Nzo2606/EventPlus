package com.eventplus.domain.registration.validations;

import com.eventplus.domain.event.EventRepository;
import com.eventplus.domain.registration.Registration;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationCapacityValidator implements RegistrationCreationData{

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void validate(RegistrationCreateData data, Registration registration) throws ValidationException {
        if (data.eventId() == null){
            throw new ValidationException("Event ID must be informed!");
        }

        var event = eventRepository.getReferenceById(data.eventId());

        if (event.getTotalParticipants() >= event.getMaxParticipants()) {
            throw new ValidationException(
                    "You cannot register! The event reached its maximum capacity."
            );
        }
    }
}

