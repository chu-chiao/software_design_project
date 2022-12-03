package com.demovehiclepro.service.booking.Notification.Commands;

import com.demovehiclepro.service.booking.Notification.INotificationHandler;

public class TestDriveOverdueNotificationCommand implements ICommand{
    INotificationHandler notificationHandler;
    Long bookingId;
    Long vehicleId;
    public TestDriveOverdueNotificationCommand(
            INotificationHandler notificationHandler, Long bookingId, Long vehicleId)
    {
        this.notificationHandler = notificationHandler;
        this.bookingId = bookingId;
        this.vehicleId = vehicleId;
    }
    @Override
    public void execute() {
        notificationHandler.sendTestDriveOverdueNotification(bookingId,vehicleId);
    }

    @Override
    public void undo() {
        notificationHandler.callBackTestDriveOverdueNotification(bookingId);
    }
}
