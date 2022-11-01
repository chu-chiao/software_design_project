package com.demovehiclepro.dtos;

import com.demovehiclepro.data.enums.BookingStatus;
import com.demovehiclepro.data.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CustomerBookingDTO extends RegistrationDTO {
    public CustomerBookingDTO(String name, String email, UserType userType) {
        super(name, email, userType);
    }

    private String location;
    private BookingStatus bookingStatus;
    private Date creationDate;
    private Long vehicleId;
}
