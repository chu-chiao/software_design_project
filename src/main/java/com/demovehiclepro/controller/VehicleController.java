package com.demovehiclepro.controller;

import com.demovehiclepro.repository.VehiclePaymentPlanRepository;
import com.demovehiclepro.repository.VehicleRepository;
import com.demovehiclepro.data.dtos.NewVehicleDTO;
import com.demovehiclepro.service.vehicle.VehicleService;
import com.demovehiclepro.service.vehicle.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    VehiclePaymentPlanRepository vehiclePaymentPlanRepository;

    @PostMapping(value = "/v1/add-vehicle", produces = "application/json")
    public ResponseEntity<?> addVehicle(@RequestBody NewVehicleDTO newVehicleDTO){
        VehicleService vehicleService = new VehicleServiceImpl(vehicleRepository, vehiclePaymentPlanRepository);
        return ResponseEntity.ok(vehicleService.addVehicle(newVehicleDTO));
    }

    @GetMapping("/v1/vehicle-list")
    public ModelAndView vehicleList(Model model) {
        VehicleService vehicleService = new VehicleServiceImpl(vehicleRepository, vehiclePaymentPlanRepository);
        model.addAttribute("vehicles", vehicleService.getVehicles());
        return new ModelAndView("list-cars");
    }
}
