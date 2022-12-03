package com.demovehiclepro.data.dtos;

import com.demovehiclepro.data.enums.UserType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Setter
@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "userType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CustomerBookingDTO.class, name = "CUSTOMER")
})
public class RegistrationDTO {

    private String name;

    @Email(message = "Email is Invalid!")
    private String email;

    @Size(min = 8)
    private String password;

    private UserType userType;

    public RegistrationDTO(String name, String email, UserType userType) {
        this.name = name;
        this.email = email;
        this.userType = userType;
    }
}
