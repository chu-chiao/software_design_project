package com.demovehiclepro.service.booking.Notification.Commands;

import com.demovehiclepro.service.booking.Notification.INotificationHandler;

public class PaymentDoneNotificationCommand implements ICommand{
    INotificationHandler notificationHandler;
    Long seId;
    Long vehicleId;

    public PaymentDoneNotificationCommand(
            INotificationHandler notificationHandler, Long seId, Long vehicleId){
        this.notificationHandler = notificationHandler;
        this.seId = seId;
        this.vehicleId = vehicleId;
    }
    @Override
    public void execute() {
        notificationHandler.sendPaymentDoneNotification(seId, vehicleId);
    }

    @Override
    public void undo() {
        notificationHandler.callBackPaymentDoneNotification(vehicleId, seId);
    }
}
