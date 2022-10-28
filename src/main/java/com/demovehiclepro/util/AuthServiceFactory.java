package com.demovehiclepro.util;

import com.demovehiclepro.data.enums.UserType;
import com.demovehiclepro.dtos.RegistrationDTO;
import com.demovehiclepro.service.authentication.AuthService;
import com.demovehiclepro.service.authentication.DealerAuthService;
import com.demovehiclepro.service.authentication.SalesExecutiveAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class AuthServiceFactory {

    @Autowired
    DealerAuthService dealerAuthService;
    SalesExecutiveAuthService salesExecutiveAuthService;

    public AuthService getAuthService(RegistrationDTO registrationDTO){

        UserType userType = registrationDTO.getUserType();
        AuthService authService = null;

        switch (userType){
            case DEALER:
                authService = dealerAuthService;
                break;
            case CUSTOMER:
                //todo: call registration method for Customer usertype

            case SALES_EXECUTIVE:
                authService = salesExecutiveAuthService;
                break;
        }

        return authService;
    }
}
