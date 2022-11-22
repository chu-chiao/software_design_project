package com.demovehiclepro.service.booking.Notification.Commands;

import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.service.booking.Notification.NotificationHandler;

public class TestDriveTakenNotificationCommand implements ICommand{
    NotificationHandler notificationHandler;
    CustomerBooking customerBooking;
    public TestDriveTakenNotificationCommand
            (NotificationHandler notificationHandler, CustomerBooking customerBooking)
    {
        this.notificationHandler = notificationHandler;
        this.customerBooking = customerBooking;
    }
    @Override
    public void send() {
        notificationHandler.sendTestDriveTakenNotification(customerBooking);
    }

    @Override
    public void unSend() {
        notificationHandler.unSendTestDriveTakenNotification(customerBooking);
    }
}
