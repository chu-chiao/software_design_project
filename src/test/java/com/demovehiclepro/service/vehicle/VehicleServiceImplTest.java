// package com.demovehiclepro.service.vehicle;

// import com.demovehiclepro.data.enums.Color;
// import com.demovehiclepro.data.enums.PaymentPlan;
// import com.demovehiclepro.data.model.Vehicle;
// import com.demovehiclepro.data.repository.VehicleRepository;
// import com.demovehiclepro.dtos.NewVehicleDTO;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import java.util.HashSet;
// import java.util.Set;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNull;

// @SpringBootTest
// public class VehicleServiceImplTest {

//     @Autowired
//     VehicleRepository vehicleRepository;

//     @Autowired
//     VehiclePaymentPlanRepository vehiclePaymentPlanRepository;

//     @Test
//     void testAddVehicle() {
//         String newVehicleModel = "CS00";
//         vehicleRepository.deleteByModelAndColor(newVehicleModel, Color.BLACK);

//         Set<PaymentPlan> paymentPlans = new HashSet<>();
//         paymentPlans.add(PaymentPlan.SIX_MONTHS_INSTALLMENT);
//         NewVehicleDTO newVehicleDTO = new NewVehicleDTO(newVehicleModel, Color.BLACK,
//                 1500.0, 6, paymentPlans);

//         long count = vehicleRepository.count();
//         Vehicle testVehicle = new Vehicle();
//         assertNull(testVehicle.getModel());
//         VehicleService vehicleService = new VehicleServiceImpl(vehicleRepository, vehiclePaymentPlanRepository);
//         testVehicle = vehicleService.addVehicle(newVehicleDTO);

//         assertEquals(newVehicleModel, testVehicle.getModel());
//         assertEquals(count + 1, vehicleRepository.count());
//     }
// }
