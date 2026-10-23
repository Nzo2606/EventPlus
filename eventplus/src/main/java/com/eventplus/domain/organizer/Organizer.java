package com.eventplus.domain.organizer;


import com.eventplus.domain.organizer.dto.OrganizerRegistrationData;
import com.eventplus.domain.organizer.dto.OrganizerUpdateData;
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

    @Column(nullable = false, unique = true, name = "company_phone")
    private String companyPhone;

    @Enumerated(EnumType.STRING)
    private BusinessType businessType;

    @Enumerated(EnumType.STRING)
    private BusinessSector businessSector;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean verified = false;

    public Organizer(OrganizerRegistrationData data) {
        super(
                data.name(),
                data.email(),
                data.password(),
                data.phone_number()
        );

        this.companyName = data.companyName();
        this.tradeName = data.tradeName();
        this.cnpj = data.cnpj();
        this.description = data.description();
        this.website = data.website();
        this.companyEmail = data.companyEmail();
        this.companyPhone = data.companyPhone();
        this.businessType = data.businessType();
        this.businessSector = data.businessSector();
    }

    public void updateData(OrganizerUpdateData data) {
        if(data.companyName() != null){
           this.companyName = data.companyName();
        }
        if(data.tradeName() != null){
            this.tradeName = data.tradeName();
        }
        if(data.cnpj() != null){
            this.cnpj = data.cnpj();
        }
        if(data.description() != null){
            this.description = data.description();
        }
        if(data.website() != null){
            this.website = data.website();
        }
        if(data.companyEmail() != null){
            this.companyEmail = data.companyEmail();
        }
        if(data.companyPhone() != null){
            this.companyPhone = data.companyPhone();
        }
        if(data.companyName() != null){
            this.businessType = data.businessType();
        }
        if(data.businessSector() != null){
            this.businessSector = data.businessSector();
        }

    }

}
