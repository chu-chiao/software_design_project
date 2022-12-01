package com.demovehiclepro.controller;

import com.demovehiclepro.data.model.Vehicle;
import com.demovehiclepro.data.repository.VehicleRepository;
import com.demovehiclepro.dtos.NewVehicleDTO;
import com.demovehiclepro.service.vehicle.VehicleService;
import com.demovehiclepro.service.vehicle.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController("vehicle")
public class VehicleController {

    @Autowired
    VehicleRepository vehicleRepository;

    @PostMapping(value = "/v1/add-vehicle", produces = "application/json")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody NewVehicleDTO newVehicleDTO){
        VehicleService vehicleService = new VehicleServiceImpl(vehicleRepository);
        return ResponseEntity.ok(vehicleService.addVehicle(newVehicleDTO));
    }

    @GetMapping("/v1/vehicle-list")
    public ModelAndView vehicleList(Model model) {
        VehicleService vehicleService = new VehicleServiceImpl(vehicleRepository);
        model.addAttribute("vehicles", vehicleService.getVehicles());
        return new ModelAndView("vehicle-list");
    }
}
