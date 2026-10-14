package com.eventplus.controller;

import com.eventplus.domain.event.EventService;
import com.eventplus.domain.event.dto.EventCancellationData;
import com.eventplus.domain.event.dto.EventDetailData;
import com.eventplus.domain.event.dto.EventListData;
import com.eventplus.domain.event.dto.EventRegistrationData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping
    public ResponseEntity<EventDetailData> register (@RequestBody @Valid EventRegistrationData data, UriComponentsBuilder uriBuilder){
        var event = eventService.register(data);

        var uri = uriBuilder.path("/event/{id}").buildAndExpand(event.id()).toUri();

        return ResponseEntity.created(uri).body(event);
    }


    @GetMapping
    public ResponseEntity<Page<EventListData>> list(@PageableDefault (size = 10, sort = {"title"})Pageable pageable){
        var event = eventService.listAll(pageable);

        return ResponseEntity.ok(event);
    }

    @GetMapping("{id}")
    public ResponseEntity<EventDetailData> detail(@PathVariable Long id){
        var event = eventService.detail(id);

        return ResponseEntity.ok(event);
    }

    @DeleteMapping
    public ResponseEntity cancel (@PathVariable EventCancellationData data){
        eventService.cancel(data);

        return ResponseEntity.noContent().build();
    }
}
