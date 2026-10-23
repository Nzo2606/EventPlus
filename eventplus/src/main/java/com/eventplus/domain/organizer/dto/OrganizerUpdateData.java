package com.eventplus.domain.organizer.dto;

import com.eventplus.domain.organizer.BusinessSector;
import com.eventplus.domain.organizer.BusinessType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record OrganizerUpdateData(
                                        @NotNull
                                        Long id,

                                        String companyName,

                                        String tradeName,

                                        @Pattern(
                                                regexp = "^[A-Za-z0-9]{8}[A-Za-z0-9]{4}\\d{2}$",
                                                message = "O CNPJ deve conter 14 caracteres alfanuméricos válidos (apenas números e letras, com os dois últimos caracteres estritamente numéricos)"
                                        )
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
