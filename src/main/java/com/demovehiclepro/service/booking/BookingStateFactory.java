package com.demovehiclepro.service.booking;

import com.demovehiclepro.data.model.CustomerBooking;

public class BookingStateFactory {
    public BookingState createBookingState(CustomerBooking customerBooking) {
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
            default:
                break;
        }
        return bookingState;
    }
}
