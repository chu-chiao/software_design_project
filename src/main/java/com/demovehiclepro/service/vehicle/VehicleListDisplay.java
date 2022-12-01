package com.demovehiclepro.service.vehicle;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;

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

    public static class VehicleData implements VehicleSubject {
        private static VehicleData vehicleData = null;
        private ArrayList<VehicleObserver> observers;

        public VehicleData() {
            observers = new ArrayList<>();
        }

        public static VehicleData getInstance()
        {
            if (vehicleData == null)
                vehicleData = new VehicleData();

            return vehicleData;
        }

        @Override
        public void attach(VehicleObserver observer) {
            observers.add(observer);
        }

        @Override
        public void detach(VehicleObserver observer) {
            int i = observers.indexOf(observer);
            if (i >= 0) {
                observers.remove(i);
            }
        }

        @Override
        public void notifyUpdate(TextMessage textMessage) {
            for (VehicleObserver observer:observers) {
                observer.update(textMessage);
            }
        }
    }
}
