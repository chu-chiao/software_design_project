package com.demovehiclepro.data.dtos;

import com.demovehiclepro.data.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
/*@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "userType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CustomerBookingDTO.class, name = "CUSTOMER")
})*/
public class RegistrationDTO {

    private String name;

    @Email(message = "Email is Invalid!")
    private String email;

    @Size(min = 8)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    public RegistrationDTO(String name, String email, UserType userType) {
        this.name = name;
        this.email = email;
        this.userType = userType;
    }
}
