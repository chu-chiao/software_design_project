package com.demovehiclepro.service.booking.Notification;

import com.demovehiclepro.data.enums.BookingStatus;
import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.service.booking.Notification.Commands.*;

public class NotificationStateFactory {
    public ICommand createNotificationCommand(BookingStatus bookingStatus, CustomerBooking customerBooking) {
        ICommand notificationCommand = null;
        INotificationHandler notificationHandler = new NotificationHandler();
        switch (bookingStatus)
        {
            case TEST_DRIVE_BOOKED:
                notificationCommand = new TestDriveBookedNotificationCommand(notificationHandler, customerBooking);
                break;
            case TEST_DRIVE_TAKEN:
                notificationCommand=new TestDriveTakenNotificationCommand(notificationHandler, customerBooking);
                break;
            case TEST_DRIVE_OVERDUE:
                notificationCommand=new TestDriveOverdueNotificationCommand(notificationHandler, customerBooking);
                break;
            case VEHICLE_BOOKED:
                notificationCommand=new VehicleBookedNotificationCommand(notificationHandler, customerBooking);
                break;
            case VEHICLE_BOOKING_CANCELLED:
                notificationCommand=new VehBookingCancelledNotificationCommand(notificationHandler, customerBooking);
                break;
            case PAYMENT_DONE:
                notificationCommand=new PaymentDoneNotificationCommand(notificationHandler, customerBooking);
                break;
        }
        return notificationCommand;
    }
}
