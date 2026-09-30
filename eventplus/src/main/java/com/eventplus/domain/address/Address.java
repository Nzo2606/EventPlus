package com.eventplus.domain.address;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Address {
    String street;
    String neighborhood;
    String zipCode;
    String city;
    String state;
    String complement;
    String number;
}
