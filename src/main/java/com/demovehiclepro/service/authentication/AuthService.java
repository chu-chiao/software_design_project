package com.demovehiclepro.service.authentication;

import com.demovehiclepro.data.model.BaseUser;
import com.demovehiclepro.dtos.RegistrationDTO;

public interface AuthService {

    BaseUser register(RegistrationDTO registrationDTO);
}
