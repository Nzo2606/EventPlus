package com.eventplus.domain.registration;

import com.eventplus.domain.event.EventRepository;
import com.eventplus.domain.event.occurrence.EventOccurrence;
import com.eventplus.domain.event.occurrence.EventOccurrenceRepository;
import com.eventplus.domain.participant.ParticipantRepository;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
import com.eventplus.domain.registration.dto.RegistrationDetailData;
import com.eventplus.domain.registration.dto.RegistrationListData;
import com.eventplus.domain.registration.enums.RegistrationMode;
import com.eventplus.domain.registration.enums.RegistrationStatus;
import com.eventplus.domain.registration.occurrence.RegistrationOccurrence;
import com.eventplus.domain.registration.validations.RegistrationCreationData;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ParticipantRepository participantRepository;

    EventOccurrenceRepository eventOccurrenceRepository;

    @Autowired
    private List<RegistrationCreationData> validators;


    // Metodo responsável por associar os IDs das ocorrências informadas no DTO aos respectivos objetos.
    private void associateOccurrences(Registration registration, RegistrationCreateData data) {

        // 1. Obtém o evento informado
        var event = registration.getEvent();

        // 2. Cria a lista para armazenar os objetos das ocorrências.
        List<EventOccurrence> occurrences;

        // 3. Define a primeira condição:
            // caso o modo de registro do evento for o evento completo...
        if (event.getRegistrationMode() == RegistrationMode.FULL_EVENT) {

                // As ocorrências do evento que o usuário escolheu são todas. Pois afinal, a inscrição abrange para todas as datas.
                occurrences = event.getOccurrences();


        }

        // 3.1. Define a segunda condição:
            // senão (no caso, se o modo for por dias selecionados, 'SELECTED_DAYS')
        else {

                // Vai ser relacionado os IDs das ocorrências recebidas pelo usuário aos objetos presentes no banco.
                occurrences = eventOccurrenceRepository.findAllById(
                        data.occurrenceIds()
                );

        }

        // 5. Percorre a lista de ocorrências, percorrendo por meio do armazenamento de cada valor da lista pela variável "occurrence".
        for (var occurrence : occurrences) {

            // Instancia classe 'RegistrationOccurrence'
            var registrationOccurrence = new RegistrationOccurrence();

            // Vai ser definido cada ocorrência, com o valor da variável "occurrence"
            registrationOccurrence.setOccurrence(occurrence);

            // Vai ser adicionado uma ocorrência dentro da lista 'registrationOccurrences'
            registration.addOccurrence(registrationOccurrence);
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public RegistrationDetailData register (RegistrationCreateData data) throws ValidationException {
        var event = eventRepository.findById(data.eventId())
                .orElseThrow(() ->
                        new ValidationException("The informed event Id does not exist!")
                );

        var participant = participantRepository.findById(data.participantId())
                .orElseThrow(() ->
                        new ValidationException("The informed participant Id does not exist!")
                );
        var registration = new Registration();
        registration.setEvent(event);
        registration.setParticipant(participant);

        for (var validator : validators) {
            validator.validate(data, registration);
        }

        event.addNewParticipant();
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
