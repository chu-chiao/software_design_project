package com.demovehiclepro.service.booking.Notification.Commands;

import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.service.booking.Notification.INotificationHandler;

public class VehBookingCancelledNotificationCommand implements ICommand{
    INotificationHandler notificationHandler;
    CustomerBooking customerBooking;

    public VehBookingCancelledNotificationCommand(INotificationHandler notificationHandler, CustomerBooking customerBooking){
        this.notificationHandler = notificationHandler;
        this.customerBooking = customerBooking;
    }
    @Override
    public void execute() {
        notificationHandler.sendNotification(customerBooking);
    }

    @Override
    public void undo() {
        notificationHandler.callBackNotification(customerBooking);
    }
}
