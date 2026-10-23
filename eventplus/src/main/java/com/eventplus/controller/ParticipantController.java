package com.eventplus.controller;

import com.eventplus.domain.participant.ParticipantService;
import com.eventplus.domain.participant.dto.ParticipantDetailData;
import com.eventplus.domain.participant.dto.ParticipantListData;
import com.eventplus.domain.participant.dto.ParticipantRegistrationData;
import com.eventplus.domain.participant.dto.ParticipantUpdateData;
import jakarta.validation.Valid;
import org.hibernate.sql.model.PreparableMutationOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

    @Autowired
    ParticipantService participantService;

    @PostMapping
    @Transactional
    public ResponseEntity<ParticipantDetailData> register(@RequestBody @Valid ParticipantRegistrationData data, UriComponentsBuilder uriBuilder){
        var participant = participantService.register(data);

        var uri = uriBuilder.path("/participant/{id}").buildAndExpand(participant.id()).toUri();

        return ResponseEntity.created(uri).body(participant);
    }

    @GetMapping
    public ResponseEntity<Page<ParticipantListData>> listAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination){
        var participant = participantService.listAll(pagination);
        return ResponseEntity.ok(participant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDetailData> detail( @PathVariable Long id){
        var participant = participantService.detail(id);
        return ResponseEntity.ok(participant);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ParticipantDetailData> update ( @RequestBody @Valid ParticipantUpdateData data){
        var participant = participantService.update(data);
        return ResponseEntity.ok(participant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete ( @PathVariable Long id){
        participantService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
