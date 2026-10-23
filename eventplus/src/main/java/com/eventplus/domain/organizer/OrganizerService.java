package com.eventplus.domain.organizer;


import com.eventplus.domain.organizer.dto.OrganizerDetailData;
import com.eventplus.domain.organizer.dto.OrganizerListData;
import com.eventplus.domain.organizer.dto.OrganizerRegistrationData;
import com.eventplus.domain.organizer.dto.OrganizerUpdateData;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizerService {

    @Autowired
    OrganizerRepository repository;

    @Transactional
    public OrganizerDetailData register(OrganizerRegistrationData data) throws ValidationException {
        if (repository.existsByCompanyNameIgnoringCase(data.companyName())){
            throw new ValidationException("This company name is already registered");
        } else if (repository.existsByCnpj(data.cnpj())) {
            throw new ValidationException("This cnpj is already registered by another company!");
        }
        var organizer = new Organizer(data);
        organizer.setActive(true);
        organizer.setVerified(false);
        repository.save(organizer);
        return new OrganizerDetailData(organizer);
    }

    public Page<OrganizerListData> listAll(Pageable pagination){
        return repository.findAll(pagination)
                .map(OrganizerListData::new);
    }

    @Transactional
    public OrganizerDetailData update(OrganizerUpdateData data) throws ValidationException {
        if (!repository.existsById(data.id())){
            throw new ValidationException("Informed organizer id does not exist!");
        }
        var organizer = repository.getReferenceById(data.id());
        organizer.updateData(data);
        return new OrganizerDetailData(organizer);
    }



    public OrganizerDetailData detail (Long id) throws ValidationException {
       if (!repository.existsById(id)){
           throw new ValidationException("Informed organizer id does not exist!");
       }
       var organizer = repository.getReferenceById(id);
       return new OrganizerDetailData(organizer);
    }

    @Transactional
    public void delete(Long id) throws ValidationException {
        if (!repository.existsById(id)){
            throw new ValidationException("The informed organizer Id does not exist!");
        }
        var organizer = repository.getReferenceById(id);
        organizer.delete();
    }
}
