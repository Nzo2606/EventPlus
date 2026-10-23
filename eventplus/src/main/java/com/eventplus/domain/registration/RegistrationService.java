package com.eventplus.domain.registration;

import com.eventplus.domain.event.EventRepository;
import com.eventplus.domain.participant.ParticipantRepository;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
import com.eventplus.domain.registration.dto.RegistrationDetailData;
import com.eventplus.domain.registration.dto.RegistrationListData;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ParticipantRepository participantRepository;


    public RegistrationDetailData register (RegistrationCreateData data) throws ValidationException {
        var event = eventRepository.findById(data.eventId())
                .orElseThrow(() ->
                        new ValidationException("The informed event Id does not exist!")
                );

        var participant = participantRepository.findById(data.eventId())
                .orElseThrow(() ->
                        new ValidationException("The informed participant Id does not exist!")
                );
        var registration = new Registration(data);
        registration.setEvent(event);
        registration.setParticipant(participant);
        registration.setStatus(RegistrationStatus.CONFIRMED);

        registrationRepository.save(registration);
        return new RegistrationDetailData(registration);
    }

    public Page<RegistrationListData> listAll (Pageable pagination){
        return registrationRepository.findAll(pagination)
                .map(RegistrationListData::new);
    }

    public RegistrationDetailData detail(Long id) throws ValidationException {
        if (!registrationRepository.existsById(id)){
            throw new ValidationException("The informed registration Id does not exist!");
        }
        var registration = registrationRepository.getReferenceById(id);
        return new RegistrationDetailData(registration);
    }
}
