package com.eventplus.domain.organizer;


import com.eventplus.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "organizer")
@Table(name = "organizer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Organizer extends User {


    @Column(nullable = false, name = "company_name")
    private String companyName;

    @Column(name = "trade_name")
    private String tradeName;

    @Column(nullable = false, unique = true)
    private String cnpj;

    private String description;

    private String website;

    @Column(nullable = false, unique = true)
    private String companyEmail;

    @Column(nullable = false, unique = true)
    private String companyPhone;

    @Enumerated(EnumType.STRING)
    private BusinessType businessType;

    @Enumerated(EnumType.STRING)
    private BusinessSector businessSector;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean verified = false;

}
