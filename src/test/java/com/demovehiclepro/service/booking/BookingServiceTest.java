package com.demovehiclepro.service.booking;

import com.demovehiclepro.data.enums.BookingStatus;
import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.exceptions.RegistrationException;
import com.demovehiclepro.repository.CustomerBookingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookingServiceTest {

    @MockBean
    CustomerBookingRepository customerBookingRepository;
    @Autowired
    BookingService bookingService;
    @Test
    void testUpdateBookingWhenRecordExists(){
        var customerBooking = new CustomerBooking();
        customerBooking.setId(2L);
        customerBooking.setLeadScore(10);
        customerBooking.setDate(new Date());
        when(customerBookingRepository.findByIdAndSalesExecutiveId(2,2))
                .thenReturn(Optional.of(customerBooking));
        customerBooking = bookingService.updateBooking(2, 2, BookingStatus.TEST_DRIVE_OVERDUE);
        assertEquals(BookingStatus.TEST_DRIVE_OVERDUE, customerBooking.getBookingStatus());
 }

 @Test
 void testUpdateBookingWhenRecordNotFoundThrowsException(){
     RegistrationException thrownException = assertThrows(
             RegistrationException.class,
             () -> bookingService.updateBooking(0, 0, BookingStatus.TEST_DRIVE_OVERDUE),
             "Expected RegistrationException, but it didn't occur"
     );
     assertTrue(thrownException.getMessage().contains("Booking is not found"));
 }

 @Test
 void testUpdateBookingWhenFailsThrowsException(){
     var customerBooking = new CustomerBooking();
     customerBooking.setId(2L);
     customerBooking.setLeadScore(10);
     customerBooking.setDate(new Date());
     customerBooking.setVehicleId(1L);
     when(customerBookingRepository.findByIdAndSalesExecutiveId(2,2))
             .thenReturn(Optional.of(customerBooking));
     when(customerBookingRepository.save(any())).thenThrow(new IllegalArgumentException());
     var customerBookingData = bookingService.updateBooking(
             2, 2, BookingStatus.TEST_DRIVE_OVERDUE);
     assertNull(customerBookingData);
 }
}
