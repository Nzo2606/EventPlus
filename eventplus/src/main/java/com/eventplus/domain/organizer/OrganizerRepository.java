package com.eventplus.domain.organizer;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrganizerRepository extends JpaRepository<Organizer, Long> {


    Boolean existsByCompanyNameIgnoringCase(@NotBlank String s);

    Boolean existsByCnpj(@NotBlank String cnpj);

}
