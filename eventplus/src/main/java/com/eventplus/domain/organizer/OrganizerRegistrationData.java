package com.eventplus.domain.organizer;

import com.eventplus.domain.address.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrganizerRegistrationData(
                                        @NotBlank
                                        String companyName,

                                        String tradeName,

                                        @NotBlank
                                        String cnpj,

                                        String description,

                                        String website,

                                        @NotBlank
                                        String companyEmail,

                                        @NotBlank
                                        String companyPhone,

                                        BusinessType businessType,

                                        BusinessSector businessSector

                                        )
{
}
