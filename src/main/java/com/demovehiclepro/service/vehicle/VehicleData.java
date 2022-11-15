package com.demovehiclepro.service.vehicle;

import org.springframework.web.socket.TextMessage;

import java.util.ArrayList;

public class VehicleData implements VehicleSubject {
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
