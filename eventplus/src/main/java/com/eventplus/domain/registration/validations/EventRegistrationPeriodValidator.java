package com.eventplus.domain.registration.validations;

import com.eventplus.domain.event.EventRepository;
import com.eventplus.domain.registration.Registration;
import com.eventplus.domain.registration.RegistrationRepository;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EventRegistrationPeriodValidator implements RegistrationCreationData{

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RegistrationRepository registrationRepository;


    @Override
    public void validate(RegistrationCreateData data, Registration registration) throws ValidationException {
        var now = LocalDateTime.now();
        var event = registration.getEvent();

        if (now.isBefore(event.getRegistrationStartDate()) ||
                now.isAfter(event.getRegistrationDeadline())) {

            throw new ValidationException(
                    "The registration period for this event is closed."
            );
        }
    }
}
