package com.eventplus.domain.participant;

import com.eventplus.domain.participant.dto.ParticipantDetailData;
import com.eventplus.domain.participant.dto.ParticipantListData;
import com.eventplus.domain.participant.dto.ParticipantRegistrationData;
import com.eventplus.domain.participant.dto.ParticipantUpdateData;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParticipantService {

    @Autowired
    ParticipantRepository repository;

    @Transactional
    public ParticipantDetailData register (ParticipantRegistrationData data){
        if (repository.existsBySsnIgnoringCase(data.ssn())){
            throw new ValidationException("The ssn should be unique! Some other participant has the same ssn");
        }
        var participant = new Participant(data);
        participant.setActive(true);
        repository.save(participant);
        return new ParticipantDetailData(participant);
    }

    public Page<ParticipantListData> listAll(Pageable pagination){
        return repository.findAll(pagination)
                            .map(ParticipantListData::new);
    }

    public ParticipantDetailData detail (Long id){
        if (!repository.existsById(id)){
            throw new ValidationException("The informed participant Id does not exist!");
        }
        var participant = repository.getReferenceById(id);
        return new ParticipantDetailData(participant);
    }

    public ParticipantDetailData update (ParticipantUpdateData data){
        if (!repository.existsById(data.id())){
            throw new ValidationException("The informed participant Id does not exist!");
        }
        var participant = repository.getReferenceById(data.id());
        participant.updateData(data);
        return new ParticipantDetailData(participant);
    }

    @Transactional
    public void delete(Long id){
        if (!repository.existsById(id)){
            throw new ValidationException("The informed participant Id does not exist!");
        }
        var participant = repository.getReferenceById(id);
        participant.delete();
    }

}
