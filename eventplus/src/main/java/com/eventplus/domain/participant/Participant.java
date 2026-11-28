package com.eventplus.domain.participant;

import com.eventplus.domain.address.Address;
import com.eventplus.domain.event.Event;
import com.eventplus.domain.participant.dto.ParticipantRegistrationData;
import com.eventplus.domain.participant.dto.ParticipantUpdateData;
import com.eventplus.domain.registration.Registration;
import com.eventplus.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "participant")
    private List<Registration> registrations = new ArrayList<>();

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

    public void addNewRegistration(Registration registration) {
        registrations.add(registration);
        registration.setParticipant(this);
    }
}
