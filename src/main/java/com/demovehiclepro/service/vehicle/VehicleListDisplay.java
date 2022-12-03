package com.demovehiclepro.service.vehicle;

import com.demovehiclepro.exceptions.VehicleException;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

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
            if (this.webSocketSession != null) this.webSocketSession.sendMessage(textMessage);
        } catch (IOException e) {
            throw new VehicleException(e.getMessage());
        }
    }
}
