package com.demovehiclepro.dtos;

import com.demovehiclepro.data.enums.UserType;
import com.sun.istack.NotNull;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
public class RegistrationDTO {
    private String name;

    private String email;

    private UserType userType;
}
