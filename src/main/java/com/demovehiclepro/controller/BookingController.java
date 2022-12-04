package com.demovehiclepro.controller;

import com.demovehiclepro.data.enums.BookingStatus;
import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/v1/updateBooking")
    public ResponseEntity<CustomerBooking> updateBooking(
            @RequestParam(name = "salesExecId") Long salesExecId,
            @RequestParam(name = "bookingId") Long bookingId,
            @RequestParam(name = "bookingStatus") BookingStatus bookingStatus)
    {
        return ResponseEntity.ok(bookingService.updateBooking(salesExecId,bookingId,bookingStatus));
    }

}
