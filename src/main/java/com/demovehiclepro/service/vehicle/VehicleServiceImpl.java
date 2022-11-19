package com.demovehiclepro.service.vehicle;

import com.demovehiclepro.data.model.Vehicle;
import com.demovehiclepro.data.repository.VehicleRepository;
import com.demovehiclepro.dtos.NewVehicleDTO;
import com.demovehiclepro.exceptions.VehicleException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;

import java.util.List;
import java.util.Optional;

public class VehicleServiceImpl implements VehicleService {

    final
    VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle addVehicle(NewVehicleDTO newVehicleDTO) {
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

        vehicleRepository.save(newVehicle);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(newVehicleDTO);
            VehicleListDisplay.VehicleData vehicleData = VehicleListDisplay.VehicleData.getInstance();
            vehicleData.notifyUpdate(new TextMessage(json));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return newVehicle;
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }
}
