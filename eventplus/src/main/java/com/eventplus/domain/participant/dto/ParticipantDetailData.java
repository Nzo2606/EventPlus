package com.eventplus.domain.participant.dto;

import com.eventplus.domain.participant.Gender;
import com.eventplus.domain.participant.Participant;

public record ParticipantDetailData(
                                    Long id,

                                    String name,

                                    String email,

                                    String phone_number,

                                    Gender gender,

                                    String ssn,

                                    String password,

                                    Boolean active
) {
    public ParticipantDetailData(Participant participant) {
        this(
                participant.getId(),
                participant.getName(),
                participant.getEmail(),
                participant.getPhone_number(),
                participant.getGender(),
                participant.getSsn(),
                participant.getPassword(),
                participant.getActive()
        );
    }
}
