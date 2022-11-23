package com.demovehiclepro.service.vehicle.paymentStrategy;

import com.demovehiclepro.data.model.VehiclePaymentPlan;

public class OneOffPaymentPlan implements PaymentPlanStrategy{

    @Override
    public Double calculatePrice(VehiclePaymentPlan vehiclePaymentPlan) {

        return vehiclePaymentPlan.getPrice();
    }
}
