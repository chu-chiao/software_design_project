package com.demovehiclepro.dtos;

import com.demovehiclepro.data.enums.UserType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "userType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CustomerBookingDTO.class, name = "CUSTOMER")
})
public class RegistrationDTO {
    private String name;

    private String email;

    private UserType userType;
}
