package com.eventplus.domain.participant.dto;

import com.eventplus.domain.participant.Gender;
import jakarta.validation.constraints.NotNull;

public record ParticipantUpdateData (
                                    @NotNull
                                    Long id,

                                    String name,

                                    String email,

                                    String phone_number,

                                    Gender gender,

                                    String ssn,

                                    String password
){
}
