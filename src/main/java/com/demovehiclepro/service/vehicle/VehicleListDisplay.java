package com.demovehiclepro.service.vehicle;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class VehicleListDisplay extends TextWebSocketHandler implements VehicleObserver {
    private VehicleSubject vehicleSubject;
    private TextMessage textMessage;
    private WebSocketSession webSocketSession;

    public VehicleListDisplay(VehicleSubject vehicleSubject) {
        this.vehicleSubject = vehicleSubject;
        vehicleSubject.attach(this);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        this.webSocketSession = session;
        System.out.println(message);
        //session.sendMessage(new TextMessage("Hi, how may we help you?"));
    }

    @Override
    public void update(TextMessage textMessage) {
        this.textMessage = textMessage;
        try {
            this.webSocketSession.sendMessage(this.textMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
