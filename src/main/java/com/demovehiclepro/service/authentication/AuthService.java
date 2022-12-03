package com.demovehiclepro.service.authentication;

import com.demovehiclepro.data.dtos.RegistrationDTO;

public interface AuthService<T> {

    T register(RegistrationDTO registrationDTO);
}
