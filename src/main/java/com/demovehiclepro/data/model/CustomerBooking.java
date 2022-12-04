package com.demovehiclepro.data.model;

import com.demovehiclepro.data.enums.BookingStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class CustomerBooking{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String location;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    private Date date;
    private Long vehicleId;
    private Long salesExecutiveId;
    private int leadScore;
}
