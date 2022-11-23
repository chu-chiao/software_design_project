package com.demovehiclepro.service.booking.Notification.Commands;

import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.service.booking.Notification.NotificationHandler;

public class TestDriveBookedNotificationCommand implements ICommand{
    NotificationHandler notificationHandler;
    CustomerBooking customerBooking;

    public TestDriveBookedNotificationCommand
            (NotificationHandler notificationHandler, CustomerBooking customerBooking)
    {
        this.notificationHandler = notificationHandler;
        this.customerBooking = customerBooking;
    }
    @Override
    public void execute() {
        notificationHandler.sendTestDriveBookedNotification(customerBooking);
    }

    @Override
    public void undo() {
        notificationHandler.unSendTestDriveBookedNotification(customerBooking);
    }
}
