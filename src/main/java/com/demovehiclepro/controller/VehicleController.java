package com.demovehiclepro.controller;

import com.demovehiclepro.dtos.NewVehicleDTO;
import com.demovehiclepro.service.authentication.VehicleService;
import com.demovehiclepro.service.authentication.VehicleServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("vehicle")
public class VehicleController {
    @PostMapping(value = "/v1/create", produces = "application/json")
    public ResponseEntity<?> createVehicle(@RequestBody NewVehicleDTO newVehicleDTO){
        VehicleService vehicleService = new VehicleServiceImpl();
        return ResponseEntity.ok(vehicleService.createVehicle(newVehicleDTO));
    }
}
