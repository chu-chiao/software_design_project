package com.demovehiclepro.service.vehicle;

import org.springframework.web.socket.TextMessage;

public interface VehicleObserver {
    public void update(TextMessage textMessage);
}
