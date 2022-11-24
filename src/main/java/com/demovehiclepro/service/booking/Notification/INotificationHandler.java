package com.demovehiclepro.service.booking.Notification;

import com.demovehiclepro.data.model.CustomerBooking;

public interface INotificationHandler {
    void sendNotification(CustomerBooking customerBooking);
    void callBackNotification(CustomerBooking customerBooking);
}
