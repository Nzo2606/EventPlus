package com.eventplus.controller;

import com.eventplus.domain.registration.RegistrationService;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
import com.eventplus.domain.registration.dto.RegistrationDetailData;
import com.eventplus.domain.registration.dto.RegistrationListData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<RegistrationDetailData> register(@RequestBody @Valid RegistrationCreateData data, UriComponentsBuilder uriBuilder) throws ValidationException {
        var registration = registrationService.register(data);

        var uri = uriBuilder.path("/registrations/{id}").buildAndExpand(registration.id()).toUri();

        return ResponseEntity.created(uri).body(registration);
    }

    @GetMapping
    public ResponseEntity<Page<RegistrationListData>> list(@PageableDefault (size = 10)Pageable pageable){
        var registration = registrationService.listAll(pageable);

        return ResponseEntity.ok(registration);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDetailData> detail(@PathVariable Long id) throws ValidationException {
        var registration = registrationService.detail(id);

        return ResponseEntity.ok(registration);
    }
}
