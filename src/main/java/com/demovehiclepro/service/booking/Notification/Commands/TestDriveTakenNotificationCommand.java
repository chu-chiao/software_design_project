package com.demovehiclepro.service.booking.Notification.Commands;

import com.demovehiclepro.service.booking.Notification.INotificationHandler;
import java.util.Date;

public class TestDriveTakenNotificationCommand implements ICommand{
    INotificationHandler notificationHandler;
    Long bookingId;
    Date testDriveDate;
    public TestDriveTakenNotificationCommand
            (INotificationHandler notificationHandler, Long bookingId, Date testDriveDate)
    {
        this.notificationHandler = notificationHandler;
        this.bookingId = bookingId;
        this.testDriveDate=testDriveDate;
    }
    @Override
    public void execute() {
        notificationHandler.sendTestDriveTakenNotification(bookingId,testDriveDate);
    }

    @Override
    public void undo() {
        notificationHandler.callBackTestDriveTakenNotification(bookingId,testDriveDate);
    }
}
