package com.eventplus.domain.registration.validations;

import com.eventplus.domain.event.EventRepository;
import com.eventplus.domain.participant.ParticipantRepository;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class ActiveParticipantValidator implements RegistrationCreationData {

    @Autowired
    ParticipantRepository participantRepository;

    @Override
    public void validate(RegistrationCreateData data) throws ValidationException {
        var participantIsActive = participantRepository.findActiveById(data.eventId());

        if (!participantIsActive){
            throw new ValidationException("Registration could not be done by an inactive event");
        }
    }
}
