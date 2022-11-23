package com.demovehiclepro.service.vehicle.paymentStrategy;

import com.demovehiclepro.data.model.VehiclePaymentPlan;

public interface PaymentPlanStrategy {

    Double calculatePrice(Double price, Double rate);
}
