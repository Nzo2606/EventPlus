package com.eventplus.domain.registration.validations;

import com.eventplus.domain.event.EventRepository;
import com.eventplus.domain.registration.RegistrationRepository;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class ParticipantWithOtherEventOnTheSameDateTimeValidator implements RegistrationCreationData {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    EventRepository eventRepository;

    @Override
    public void validate(RegistrationCreateData data) throws ValidationException {
        var event = eventRepository.getReferenceById(data.eventId());
        var firstTimeSlot = event.getStartDate();
        var lastTimeSlot = event.getEndDate();

        var participantHasOtherRegistrationOnDateTime = registrationRepository.existsByParticipantIdAndDateBetween(data.participantId(), firstTimeSlot, lastTimeSlot);
        if (participantHasOtherRegistrationOnDateTime){
            throw new ValidationException("This participant already has a scheduled registration on the this date time");
        }
    }
}
