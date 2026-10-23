package com.eventplus.domain.participant.dto;

import com.eventplus.domain.participant.Participant;

public record ParticipantListData (
                                    Long id,

                                    String name,

                                    String email,

                                    String phone_number
){
    public ParticipantListData(Participant participant) {
        this(
                participant.getId(),
                participant.getName(),
                participant.getEmail(),
                participant.getPhone_number()
        );
    }
}
