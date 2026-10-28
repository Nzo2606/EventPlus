package com.eventplus.domain.event;

import com.eventplus.domain.category.CategoryRepository;
import com.eventplus.domain.event.dto.cancellation.EventCancellationData;
import com.eventplus.domain.event.dto.detail.EventDetailData;
import com.eventplus.domain.event.dto.list.EventListData;
import com.eventplus.domain.event.dto.registration.EventRegistrationData;
import com.eventplus.domain.event.dto.update.EventUpdateData;
import com.eventplus.domain.organizer.OrganizerRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    OrganizerRepository organizerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Transactional
    // REGISTRAR EVENTO
    public EventDetailData register(EventRegistrationData data){

        var category = categoryRepository.findById(data.categoryId())
                .orElseThrow(() ->
                        new ValidationException("Informed category Id does not exist!"));

        var organizer = organizerRepository.findById(data.organizerId())
                .orElseThrow(() ->
                        new ValidationException("Informed organizer Id does not exist!"));

        var event = new Event(data);
        event.setCategory(category);
        event.setOrganizer(organizer);

        event.updateStatus();
        eventRepository.save(event);

        return new EventDetailData(event);
    }

    // LISTAR EVENTOS
    public Page<EventListData> listAll (Pageable pagination){
        return eventRepository.findAll(pagination)
                .map(EventListData::new);
    }

    // DETALHAR EVENTO
    public EventDetailData detail(Long id){
        if (!eventRepository.existsById(id)){
            throw new ValidationException("The informed event Id does not exist!");
        }
        var event = eventRepository.getReferenceById(id);
        return new EventDetailData(event);
    }

    @Transactional
    public EventDetailData update(EventUpdateData data){
        if (!eventRepository.existsById(data.id())){
            throw new ValidationException("Informed event Id does not exist!");
        }

        var event = eventRepository.getReferenceById(data.id());

        event.updateData(data);

        if (data.organizerId() != null) {
            var organizer = organizerRepository.findById(data.organizerId())
                    .orElseThrow(() ->
                            new ValidationException("Informed organizer Id does not exist!"));

            event.setOrganizer(organizer);
        }

        if (data.categoryId() != null) {
            var category = categoryRepository.findById(data.categoryId())
                    .orElseThrow(() ->
                            new ValidationException("Informed category Id does not exist!"));

            event.setCategory(category);
        }

        event.updateStatus();
        return new EventDetailData(event);

    }

    @Transactional
    // CANCELAR EVENTO
    public void cancel (@Valid EventCancellationData data){
        if (!eventRepository.existsById(data.eventId())){
            throw new ValidationException("Informed event Id does not exist!");
        }
        var event = eventRepository.getReferenceById(data.eventId());
        event.cancel(data.reason());
        event.updateStatus();
    }
}
