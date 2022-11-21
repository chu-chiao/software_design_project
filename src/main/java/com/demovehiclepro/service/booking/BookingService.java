package com.demovehiclepro.service.booking;

import com.demovehiclepro.data.enums.BookingStatus;
import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.data.repository.CustomerBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookingService {


    @Autowired
    CustomerBookingRepository customerBookingRepository;
    public CustomerBooking UpdateBooking(long salesExecId, long bookingId, BookingStatus bookingStatus, Date date){
        var customerBooking=
                customerBookingRepository.findByIdAndSalesExecutiveId(bookingId,salesExecId).get();
        if(customerBooking == null)
        {
            throw new RuntimeException("Booking is not assigned to the sales executive");
        }

        BookingState bookingState = GetBookingState(customerBooking);
        customerBooking.setLeadScore(bookingState.CalculateLeadScore(customerBooking.getLeadScore()));
        customerBooking.setBookingStatus(bookingStatus);
        customerBooking.setDate(date);
        customerBookingRepository.save(customerBooking);
        return customerBooking;
    }

    private BookingState GetBookingState(CustomerBooking customerBooking) {
        BookingState bookingState = null;
        switch (customerBooking.getBookingStatus()){
            case TEST_DRIVE_OVERDUE:
                bookingState=new TestDriveOverdueState(customerBooking.getDate());
                break;
            case TEST_DRIVE_TAKEN:
                bookingState=new TestDriveTakenState();
                break;
            case VEHICLE_BOOKED:
                bookingState=new VehicleBookedState(customerBooking.getDate());
                break;
            case VEHICLE_BOOKING_CANCELLED:
                bookingState=new VehicleBookingCancelled();
                break;
            case PAYMENT_DONE:
                bookingState=new PaymentDoneState();
                break;
        }
        return bookingState;
    }
}
