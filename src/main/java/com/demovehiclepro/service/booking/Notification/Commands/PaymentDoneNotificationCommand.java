package com.demovehiclepro.service.booking.Notification.Commands;

import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.service.booking.Notification.NotificationHandler;

public class PaymentDoneNotificationCommand implements ICommand{
    NotificationHandler notificationHandler;
    CustomerBooking customerBooking;

    public PaymentDoneNotificationCommand(NotificationHandler notificationHandler, CustomerBooking customerBooking){
        this.notificationHandler = notificationHandler;
        this.customerBooking = customerBooking;
    }
    @Override
    public void send() {
        notificationHandler.sendPaymentDoneNotification(customerBooking);
    }

    @Override
    public void unSend() {
        notificationHandler.unSendPaymentDoneNotification(customerBooking);
    }
}
