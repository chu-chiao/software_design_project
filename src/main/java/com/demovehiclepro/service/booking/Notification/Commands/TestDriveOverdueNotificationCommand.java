package com.demovehiclepro.service.booking.Notification.Commands;

import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.service.booking.Notification.NotificationHandler;

public class TestDriveOverdueNotificationCommand implements ICommand{
    NotificationHandler notificationHandler;
    CustomerBooking customerBooking;
    public TestDriveOverdueNotificationCommand(NotificationHandler notificationHandler,CustomerBooking customerBooking)
    {
        this.notificationHandler = notificationHandler;
        this.customerBooking = customerBooking;
    }
    @Override
    public void execute() {
        notificationHandler.sendTestDriveOverdueNotification(customerBooking);
    }

    @Override
    public void undo() {
        notificationHandler.unSendTestDriveOverdueNotification(customerBooking);
    }
}
