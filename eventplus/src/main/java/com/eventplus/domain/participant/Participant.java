package com.eventplus.domain.participant;

import com.eventplus.domain.address.Address;
import com.eventplus.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "participant")
@Table(name = "participant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Participant extends User{

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, unique = true)
    private String ssn;

}
