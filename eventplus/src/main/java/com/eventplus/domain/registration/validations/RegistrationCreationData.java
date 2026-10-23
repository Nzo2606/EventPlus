package com.eventplus.domain.registration.validations;

import com.eventplus.domain.registration.dto.RegistrationCreateData;
import jakarta.xml.bind.ValidationException;

public interface RegistrationCreationData {

    void validate (RegistrationCreateData data) throws ValidationException;
}
