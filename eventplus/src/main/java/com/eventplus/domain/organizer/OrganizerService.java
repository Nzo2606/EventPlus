package com.eventplus.domain.organizer;


import com.eventplus.domain.organizer.dto.OrganizerDetailData;
import com.eventplus.domain.organizer.dto.OrganizerListData;
import com.eventplus.domain.organizer.dto.OrganizerRegistrationData;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService {

    @Autowired
    OrganizerRepository repository;

    public OrganizerDetailData register(OrganizerRegistrationData data) throws ValidationException {
        if (repository.existsByCompanyNameIgnoringCase(data.companyName())){
            throw new ValidationException("This company name is already registered");
        } else if (repository.existsByCnpj(data.cnpj())) {
            throw new ValidationException("This cnpj is already registered by another company!");
        }
        var organizer = new Organizer(data);
        repository.save(organizer);
        return new OrganizerDetailData(organizer);
    }

    public Page<OrganizerListData> listAll(Pageable pagination){
        return repository.findAll(pagination)
                .map(OrganizerListData::new);
    }

    public OrganizerDetailData detail (Long id) throws ValidationException {
       if (!repository.existsById(id)){
           throw new ValidationException("Informed organizer id does not exist!");
       }
       var organizer = repository.getReferenceById(id);
       return new OrganizerDetailData(organizer);
    }
}
