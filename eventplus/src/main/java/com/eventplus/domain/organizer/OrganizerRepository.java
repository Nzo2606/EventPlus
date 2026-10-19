package com.eventplus.domain.organizer;

import com.eventplus.domain.event.Event;
import com.eventplus.domain.organizer.dto.OrganizerListData;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {


    Boolean existsByCompanyNameIgnoringCase(@NotBlank String s);

    Boolean existsByCnpj(@NotBlank String cnpj);

    Page<Organizer> findAll(Pageable pagination);
}
