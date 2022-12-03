package com.demovehiclepro.service.booking.Notification.Commands;

import com.demovehiclepro.service.booking.Notification.INotificationHandler;
import java.util.Date;

public class TestDriveBookedNotificationCommand implements ICommand{
    INotificationHandler notificationHandler;
    Long bookingId;
    Long vehicleId;
    Date bookedDate;

    public TestDriveBookedNotificationCommand(
            INotificationHandler notificationHandler, Long bookingId,Long vehicleId, Date bookedDate)
    {
        this.notificationHandler = notificationHandler;
        this.bookingId = bookingId;
        this.vehicleId = vehicleId;
        this.bookedDate=bookedDate;
    }
    @Override
    public void execute() {
        notificationHandler.sendTestDriveBookedNotification(bookingId,vehicleId,bookedDate);
    }

    @Override
    public void undo() {
        notificationHandler.callBackTestDriveBookedNotification(bookingId, vehicleId);
    }
}
