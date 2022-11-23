package com.demovehiclepro.service.booking.Notification.Commands;

import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.service.booking.Notification.NotificationHandler;

public class VehBookingCancelledNotificationCommand implements ICommand{
    NotificationHandler notificationHandler;
    CustomerBooking customerBooking;

    public VehBookingCancelledNotificationCommand(NotificationHandler notificationHandler, CustomerBooking customerBooking){
        this.notificationHandler = notificationHandler;
        this.customerBooking = customerBooking;
    }
    @Override
    public void execute() {
        notificationHandler.sendVehBookingCancelledNotification(customerBooking);
    }

    @Override
    public void undo() {
        notificationHandler.unSendVehBookingCancelledNotification(customerBooking);
    }
}
