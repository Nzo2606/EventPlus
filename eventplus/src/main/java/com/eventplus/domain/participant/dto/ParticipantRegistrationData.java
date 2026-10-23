package com.eventplus.domain.participant.dto;

import com.eventplus.domain.participant.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ParticipantRegistrationData (
                                            // Campos herdados de User
                                            @NotBlank
                                            String name,

                                            @NotBlank
                                            @Email
                                            String email,

                                            @NotBlank
                                            String password,

                                            @NotBlank
                                            String phone_number,

                                            @NotNull
                                            Gender gender,

                                            @NotBlank
                                            @Pattern(regexp = "\\d{11}")
                                            String ssn




){
}
