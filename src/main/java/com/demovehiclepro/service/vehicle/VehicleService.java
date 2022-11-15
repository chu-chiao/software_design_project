package com.demovehiclepro.service.vehicle;

import com.demovehiclepro.data.model.Vehicle;
import com.demovehiclepro.dtos.NewVehicleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {
    Vehicle addVehicle(NewVehicleDTO newVehicleDTO);

    List<Vehicle> getVehicles();
}
