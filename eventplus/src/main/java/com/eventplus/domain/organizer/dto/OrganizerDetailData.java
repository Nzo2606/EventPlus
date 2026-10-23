package com.eventplus.domain.organizer.dto;

import com.eventplus.domain.organizer.BusinessSector;
import com.eventplus.domain.organizer.BusinessType;
import com.eventplus.domain.organizer.Organizer;
import jakarta.validation.constraints.NotBlank;

public record OrganizerDetailData(
                                        Long id,

                                        String companyName,

                                        String tradeName,

                                        String cnpj,

                                        String description,

                                        String website,

                                        String companyEmail,

                                        String companyPhone,

                                        BusinessType businessType,

                                        BusinessSector businessSector,

                                        Boolean active

                                        )
{
    public OrganizerDetailData(Organizer organizer) {
        this(
                organizer.getId(),
                organizer.getCompanyName(),
                organizer.getTradeName(),
                organizer.getCnpj(),
                organizer.getDescription(),
                organizer.getWebsite(),
                organizer.getCompanyEmail(),
                organizer.getCompanyPhone(),
                organizer.getBusinessType(),
                organizer.getBusinessSector(),
                organizer.getActive()
        );
    }
}
