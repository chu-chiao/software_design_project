package com.demovehiclepro.service.vehicle;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;

public class VehicleListDisplay extends TextWebSocketHandler implements VehicleObserver {
    private WebSocketSession webSocketSession;

    public VehicleListDisplay(VehicleSubject vehicleSubject) {
        vehicleSubject.attach(this);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        this.webSocketSession = session;
        System.out.println(message);
    }

    @Override
    public void update(TextMessage textMessage) {
        try {
            this.webSocketSession.sendMessage(textMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
