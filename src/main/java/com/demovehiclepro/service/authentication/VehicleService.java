package com.demovehiclepro.service.authentication;

import com.demovehiclepro.data.model.Vehicle;
import com.demovehiclepro.dtos.NewVehicleDTO;

public interface VehicleService {
    Vehicle createVehicle(NewVehicleDTO newVehicleDTO);
}
