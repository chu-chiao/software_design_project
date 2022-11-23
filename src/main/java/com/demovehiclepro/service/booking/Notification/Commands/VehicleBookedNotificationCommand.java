package com.demovehiclepro.service.booking.Notification.Commands;

import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.service.booking.Notification.NotificationHandler;

public class VehicleBookedNotificationCommand implements ICommand{
    NotificationHandler notificationHandler;
    CustomerBooking customerBooking;

    public VehicleBookedNotificationCommand(NotificationHandler notificationHandler, CustomerBooking customerBooking){
        this.notificationHandler = notificationHandler;
        this.customerBooking = customerBooking;
    }
    @Override
    public void execute() {
        notificationHandler.sendVehicleBookedNotification(customerBooking);
    }

    @Override
    public void undo() {
        notificationHandler.unSendVehicleBookedNotification(customerBooking);
    }
}
