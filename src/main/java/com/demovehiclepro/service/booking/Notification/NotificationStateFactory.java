package com.demovehiclepro.service.booking.Notification;

import com.demovehiclepro.data.enums.BookingStatus;
import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.service.booking.Notification.Commands.*;

public class NotificationStateFactory {
    private NotificationStateFactory(){}
    public static ICommand createNotificationCommand(BookingStatus bookingStatus, CustomerBooking customerBooking) {
        ICommand notificationCommand;
        INotificationHandler notificationHandler = new NotificationHandler();
        switch (bookingStatus) {
            case TEST_DRIVE_BOOKED:
                notificationCommand = new TestDriveBookedNotificationCommand(
                        notificationHandler, customerBooking.getId(), customerBooking.getVehicleId(), customerBooking.getDate());
                break;
            case TEST_DRIVE_TAKEN:
                notificationCommand=new TestDriveTakenNotificationCommand(notificationHandler, customerBooking.getId(), customerBooking.getDate());
                break;
            case TEST_DRIVE_OVERDUE:
                notificationCommand=new TestDriveOverdueNotificationCommand(notificationHandler, customerBooking.getId(), customerBooking.getVehicleId());
                break;
            case VEHICLE_BOOKED:
                notificationCommand=new VehicleBookedNotificationCommand(notificationHandler, customerBooking.getVehicleId(), customerBooking.getDate());
                break;
            case VEHICLE_BOOKING_CANCELLED:
                notificationCommand=new VehBookingCancelledNotificationCommand(notificationHandler, customerBooking.getVehicleId(), customerBooking.getLocation());
                break;
            case PAYMENT_DONE:
                notificationCommand=new PaymentDoneNotificationCommand(notificationHandler, customerBooking.getSalesExecutiveId(), customerBooking.getVehicleId());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + bookingStatus);
        }
        return notificationCommand;
    }
}
