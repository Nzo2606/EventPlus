package com.eventplus.controller;

import com.eventplus.domain.organizer.OrganizerService;
import com.eventplus.domain.organizer.dto.OrganizerDetailData;
import com.eventplus.domain.organizer.dto.OrganizerListData;
import com.eventplus.domain.organizer.dto.OrganizerRegistrationData;
import jakarta.validation.Valid;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {

    @Autowired
    OrganizerService organizerService;

    @PostMapping
    public ResponseEntity<OrganizerDetailData> register (@RequestBody @Valid OrganizerRegistrationData data, UriComponentsBuilder uriBuilder) throws ValidationException {
        var organizer = organizerService.register(data);

        var uri = uriBuilder.path("/organizer/{id}").buildAndExpand(organizer.id()).toUri();

        return ResponseEntity.created(uri).body(organizer);
    }


    @GetMapping
    public ResponseEntity<Page<OrganizerListData>> list(@PageableDefault(size = 10, sort = {"companyName"}) Pageable pageable){
        var event = organizerService.listAll(pageable);

        return ResponseEntity.ok(event);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizerDetailData> detail(@PathVariable Long id) throws ValidationException {
        var event = organizerService.detail(id);

        return ResponseEntity.ok(event);
    }
}
