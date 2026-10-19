package com.eventplus.domain.organizer.dto;

import com.eventplus.domain.organizer.BusinessSector;
import com.eventplus.domain.organizer.BusinessType;
import com.eventplus.domain.organizer.Organizer;
import jakarta.validation.constraints.NotBlank;

public record OrganizerListData(
                                        String companyName,

                                        String cnpj,

                                        String companyEmail,

                                        String companyPhone,

                                        BusinessType businessType,

                                        BusinessSector businessSector

                                        )
{
    public OrganizerListData(Organizer organizer) {
        this(
                organizer.getCompanyName(),
                organizer.getCnpj(),
                organizer.getCompanyEmail(),
                organizer.getCompanyPhone(),
                organizer.getBusinessType(),
                organizer.getBusinessSector()
        );
    }
}
