package com.demovehiclepro.service.booking.Notification.Commands;

import com.demovehiclepro.service.booking.Notification.INotificationHandler;

public class VehBookingCancelledNotificationCommand implements ICommand{
    INotificationHandler notificationHandler;
    Long vehicleId;
    String location;

    public VehBookingCancelledNotificationCommand(
            INotificationHandler notificationHandler, Long vehicleId, String location){
        this.notificationHandler = notificationHandler;
        this.location = location;
        this.vehicleId = vehicleId;
    }
    @Override
    public void execute() {
        notificationHandler.sendVehBookingCancelledNotification(vehicleId, location);
    }

    @Override
    public void undo() {
        notificationHandler.callBackVehBookingCancelledNotification(vehicleId, location);
    }
}
