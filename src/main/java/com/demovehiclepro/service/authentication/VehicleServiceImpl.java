package com.demovehiclepro.service.authentication;

import com.demovehiclepro.data.enums.Color;
import com.demovehiclepro.data.enums.PaymentPlan;
import com.demovehiclepro.data.model.Vehicle;
import com.demovehiclepro.data.repository.VehicleRepository;
import com.demovehiclepro.dtos.NewVehicleDTO;
import com.demovehiclepro.exceptions.VehicleException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public Vehicle createVehicle(NewVehicleDTO newVehicleDTO) {
        Optional<Vehicle> optionalVehicle
                = vehicleRepository.findByModelAndColor(newVehicleDTO.getModel(), newVehicleDTO.getColor());
        if (optionalVehicle.isPresent()) {
            throw new VehicleException("Create Failed! Model already exists");
        }

        Vehicle newVehicle = new Vehicle();
        newVehicle.setModel(newVehicleDTO.getModel());
        newVehicle.setColor(newVehicleDTO.getColor());
        newVehicle.setPrice(newVehicleDTO.getPrice());
        newVehicle.setCapacity(newVehicleDTO.getCapacity());
        newVehicle.setPaymentPlan(newVehicleDTO.getPaymentPlan());
        return null;
    }
}
