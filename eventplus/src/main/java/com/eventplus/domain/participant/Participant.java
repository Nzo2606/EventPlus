package com.eventplus.domain.participant;

import com.eventplus.domain.address.Address;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "participant")
@Table(name = "participant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone_number;

    private String ssn;

    @Embedded
    private Address address;

}
