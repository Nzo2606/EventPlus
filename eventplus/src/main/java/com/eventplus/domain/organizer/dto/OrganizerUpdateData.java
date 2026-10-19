package com.eventplus.domain.organizer.dto;

import com.eventplus.domain.organizer.BusinessSector;
import com.eventplus.domain.organizer.BusinessType;
import jakarta.validation.constraints.NotBlank;

public record OrganizerUpdateData(
                                        String companyName,

                                        String tradeName,

                                        String cnpj,

                                        String description,

                                        String website,

                                        String companyEmail,

                                        String companyPhone,

                                        BusinessType businessType,

                                        BusinessSector businessSector

                                        )
{
}
