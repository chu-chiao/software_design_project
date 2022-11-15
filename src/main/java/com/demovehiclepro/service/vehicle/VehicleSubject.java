package com.demovehiclepro.service.vehicle;

import org.springframework.web.socket.TextMessage;

public interface VehicleSubject {
    public void attach(VehicleObserver observer);
    public void detach(VehicleObserver observer);
    public void notifyUpdate(TextMessage textMessage);
}
