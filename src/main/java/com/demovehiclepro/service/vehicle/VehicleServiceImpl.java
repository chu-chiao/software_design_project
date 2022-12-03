package com.demovehiclepro.service.vehicle;

import com.demovehiclepro.data.enums.Color;
import com.demovehiclepro.data.enums.PaymentPlan;
import com.demovehiclepro.data.model.Vehicle;
import com.demovehiclepro.data.model.VehiclePaymentPlan;
import com.demovehiclepro.repository.VehiclePaymentPlanRepository;
import com.demovehiclepro.repository.VehicleRepository;
import com.demovehiclepro.data.dtos.NewVehicleDTO;
import com.demovehiclepro.exceptions.VehicleException;
import com.demovehiclepro.service.vehicle.paymentStrategy.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("VehicleService")
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    VehiclePaymentPlanRepository vehiclePaymentPlanRepository;

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

        Set<PaymentPlan> paymentPlans = newVehicleDTO.getPaymentPlan();
        Set<VehiclePaymentPlan> processedPaymentPlans =  processPaymentPlan(newVehicleDTO, paymentPlans);
        newVehicle.setVehiclePaymentPlans(processedPaymentPlans);

        vehicleRepository.save(newVehicle);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(newVehicleDTO);
            VehicleData vehicleData = VehicleData.getInstance();
            vehicleData.notifyUpdate(new TextMessage(json));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return newVehicle;
    }

    private Set<VehiclePaymentPlan> processPaymentPlan(NewVehicleDTO newVehicleDTO, Set<PaymentPlan> paymentPlans){
        Set<VehiclePaymentPlan> vehiclePaymentPlans = new HashSet<>();

        paymentPlans.forEach(paymentPlan -> {
            Double priceToPay = null;
            VehiclePaymentPlan vehiclePaymentPlan = new VehiclePaymentPlan(newVehicleDTO.getPrice());

            switch (paymentPlan){
                case ONE_OFF:
                    vehiclePaymentPlan.setPaymentPlanName(PaymentPlan.ONE_OFF);
                    priceToPay = vehiclePaymentPlan.calculatePriceToPay(new OneOffPaymentPlan());

                    break;

                case FOUR_MONTHS_INSTALLMENT:
                    vehiclePaymentPlan.setPaymentPlanName(PaymentPlan.FOUR_MONTHS_INSTALLMENT);
                    vehiclePaymentPlan.setInterestRate(0.10);
                    priceToPay = vehiclePaymentPlan.calculatePriceToPay(new FourMonthsPaymentPlan());

                    break;

                case SIX_MONTHS_INSTALLMENT:
                    vehiclePaymentPlan.setPaymentPlanName(PaymentPlan.SIX_MONTHS_INSTALLMENT);
                    vehiclePaymentPlan.setInterestRate(0.12);
                    priceToPay = vehiclePaymentPlan.calculatePriceToPay(new SixMonthsPaymentPlan());

                    break;
            }

            vehiclePaymentPlan.setPriceToPay(priceToPay);
            VehiclePaymentPlan savedPaymentPlan = vehiclePaymentPlanRepository.save(vehiclePaymentPlan);

            vehiclePaymentPlans.add(savedPaymentPlan);
        });

        return vehiclePaymentPlans;
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public void deleteByModelAndColor(String model, Color color) {
        vehicleRepository.deleteByModelAndColor(model, color);
    }

    @Override
    public long getCount() {
        return vehicleRepository.count();
    }
}
