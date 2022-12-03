package com.demovehiclepro.service.booking.Notification.Commands;

import com.demovehiclepro.service.booking.Notification.INotificationHandler;

import java.util.Date;

public class VehicleBookedNotificationCommand implements ICommand{
    INotificationHandler notificationHandler;
    Long vehicleId;
    Date vehicleBookedDate;

    public VehicleBookedNotificationCommand(
            INotificationHandler notificationHandler, Long vehicleId, Date vehicleBookedDate){
        this.notificationHandler = notificationHandler;
        this.vehicleId = vehicleId;
        this.vehicleBookedDate = vehicleBookedDate;
    }
    @Override
    public void execute() {
        notificationHandler.sendVehicleBookedNotification(vehicleId,vehicleBookedDate);
    }

    @Override
    public void undo() {
        notificationHandler.callBackVehBookedNotification(vehicleId);
    }
}
