package com.eventplus.domain.user;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "user")
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String phone_number;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean active;


    public User(String name, String email, String password, String phone_number) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
    }

    public void delete(){
        this.active = false;
    }
}
