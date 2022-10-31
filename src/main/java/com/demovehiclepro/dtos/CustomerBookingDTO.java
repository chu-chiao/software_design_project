package com.demovehiclepro.dtos;

import com.demovehiclepro.data.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerBookingDTO extends RegistrationDTO {
    public CustomerBookingDTO(String name, String email, UserType userType) {
        super(name, email, userType);
    }

    private String location;
    private String model;
}
