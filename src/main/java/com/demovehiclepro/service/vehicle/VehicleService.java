package com.demovehiclepro.service.vehicle;

import com.demovehiclepro.data.enums.Color;
import com.demovehiclepro.data.model.Vehicle;
import com.demovehiclepro.data.dtos.NewVehicleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {
    Vehicle addVehicle(NewVehicleDTO newVehicleDTO);
    List<Vehicle> getVehicles();
    void deleteByModelAndColor(String model, Color color);
    long getCount();
}
