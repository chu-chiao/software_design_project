package com.demovehiclepro.service.booking.Notification;

import com.demovehiclepro.data.model.CustomerBooking;

public class NotificationHandler implements INotificationHandler{
    @Override
    public void sendNotification(CustomerBooking customerBooking) {
        System.out.printf(
                "Status of your booking {0} has been changed to {1} on {2}.%n",
                customerBooking.getId(),customerBooking.getBookingStatus(), customerBooking.getDate());
    }

    @Override
    public void callBackNotification(CustomerBooking customerBooking) {
        System.out.printf(
                "Mail sent for the booking {0} of state {1} for model {2} is called back%n",
                customerBooking.getId(), customerBooking.getBookingStatus(), customerBooking.getVehicleId());
    }
}
