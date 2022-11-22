package com.demovehiclepro.service.booking.Notification;

import com.demovehiclepro.service.booking.Notification.Commands.ICommand;

public class NotificationService {
    ICommand command;
    public void setCommand(ICommand notificationCommand)
    {
        this.command = notificationCommand;
    }
    public void executeSendCommand()
    {
        command.send();
    }
    public void executeUnSendCommand()
    {
        command.unSend();
    }
}
