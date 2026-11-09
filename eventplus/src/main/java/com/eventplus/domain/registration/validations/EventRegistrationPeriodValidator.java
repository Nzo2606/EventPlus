package com.eventplus.domain.registration.validations;

import com.eventplus.domain.event.EventRepository;
import com.eventplus.domain.registration.Registration;
import com.eventplus.domain.registration.RegistrationRepository;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventRegistrationPeriodValidator implements RegistrationCreationData{

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RegistrationRepository registrationRepository;


    @Override
    public void validate(RegistrationCreateData data, Registration registration) throws ValidationException {
        var event = eventRepository.getReferenceById(data.eventId());

        if (registration.getRegistrationDate().isAfter(event.getRegistrationDeadline())){
            throw new ValidationException("Error! The registration period for this event has already ended.");
        }
    }
}
