package com.demovehiclepro.util;

import com.demovehiclepro.data.enums.UserType;
import com.demovehiclepro.data.dtos.RegistrationDTO;
import com.demovehiclepro.service.authentication.AuthService;
import com.demovehiclepro.service.authentication.CustomerBookingAuthService;
import com.demovehiclepro.service.authentication.DealerAuthService;
import com.demovehiclepro.service.authentication.SalesExecutiveAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class AuthServiceFactory {

    @Autowired
    DealerAuthService dealerAuthService;

    @Autowired
    SalesExecutiveAuthService salesExecutiveAuthService;

    @Autowired
    CustomerBookingAuthService customerBookingAuthService;

    public AuthService createAuthService(RegistrationDTO registrationDTO){

        UserType userType = registrationDTO.getUserType();
        AuthService authService = null;

        switch (userType){
            case DEALER:
                authService = dealerAuthService;
                break;
            case CUSTOMER:
                authService = customerBookingAuthService;
                break;
            case SALES_EXECUTIVE:
                authService = salesExecutiveAuthService;
                break;
            default: //todo:  add default case for registration
        }

        return authService;
    }
}
