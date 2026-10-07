package com.eventplus.domain.organizer;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "organizer")
@Table(name = "organizer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Organizer {

    @Column(nullable = false)
    private String companyName;

    private String tradeName;

    @Column(nullable = false, unique = true)
    private String cnpj;

    private String description;

    private String website;

    @Column(nullable = false, unique = true)
    private String companyEmail;

    @Column(nullable = false, unique = true)
    private String companyPhone;

    private String responsibleName;

    @Enumerated(EnumType.STRING)
    private BusinessType businessType;

    @Enumerated(EnumType.STRING)
    private BusinessSector businessSector;

    private Boolean verified = false;

}
