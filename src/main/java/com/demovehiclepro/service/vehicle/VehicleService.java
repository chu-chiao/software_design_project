package com.demovehiclepro.service.vehicle;

import com.demovehiclepro.data.model.Vehicle;
import com.demovehiclepro.dtos.NewVehicleDTO;

import java.util.List;

public interface VehicleService {
    Vehicle addVehicle(NewVehicleDTO newVehicleDTO);

    List<Vehicle> getVehicles();
}
