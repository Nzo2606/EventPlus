package com.eventplus.domain.registration.validations;

import com.eventplus.domain.event.EventRepository;
import com.eventplus.domain.participant.ParticipantRepository;
import com.eventplus.domain.registration.Registration;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrationDuplicationValidator implements RegistrationCreationData{

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    EventRepository eventRepository;

    @Override
    public void validate(RegistrationCreateData data, Registration registration) throws ValidationException {
        var participant = participantRepository.getReferenceById(data.participantId());
        var selectedEvent = eventRepository.getReferenceById(data.eventId());

        participant.getEvents().forEach(event -> {
            if (event.getId().equals(selectedEvent.getId())){
                try {
                    throw new ValidationException("You can not register twice in a event!");
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
