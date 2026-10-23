package com.eventplus.domain.participant;

import com.eventplus.domain.address.Address;
import com.eventplus.domain.participant.dto.ParticipantRegistrationData;
import com.eventplus.domain.participant.dto.ParticipantUpdateData;
import com.eventplus.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "participant")
@Table(name = "participant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Participant extends User{

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, unique = true)
    private String ssn;

    public Participant(ParticipantRegistrationData data) {
        super(
                data.name(),
                data.email(),
                data.password(),
                data.phone_number()
        );
        this.gender = data.gender();
        this.ssn = data.ssn();
    }

    public void updateData(ParticipantUpdateData data) {
        if (data.name() != null){
            setName(data.name());
        }
        if (data.email() != null){
            setEmail(data.email());
        }
        if (data.phone_number() != null){
            setPhone_number(data.phone_number());
        }
        if (data.password() != null){
            setPassword(data.password());
        }
        if (data.gender() != null){
            this.gender = data.gender();
        }
        if (data.ssn() != null){
            this.ssn = data.ssn();
        }
    }


}
