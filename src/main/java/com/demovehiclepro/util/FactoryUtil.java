package com.demovehiclepro.util;

import com.demovehiclepro.data.enums.UserType;
import com.demovehiclepro.data.model.BaseUser;
import com.demovehiclepro.dtos.RegistrationDTO;
import com.demovehiclepro.service.authentication.DealerAuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class FactoryUtil {

    @Autowired
    DealerAuthServiceImpl dealerAuthService;

    public BaseUser register(RegistrationDTO registrationDTO){
        UserType userType = registrationDTO.getUserType();
        BaseUser user = null;

        switch (userType){
            case DEALER:
                user = dealerAuthService.register(registrationDTO);

            case CUSTOMER:
                //todo: call registration method for Customer usertype

            case SALES_EXECUTIVE:
                //todo: call registration method for Sales Executive usertype
        }

        return user;
    }
}
