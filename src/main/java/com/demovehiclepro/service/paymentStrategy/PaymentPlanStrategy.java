package com.demovehiclepro.service.paymentStrategy;

import com.demovehiclepro.data.model.VehiclePaymentPlan;

public interface PaymentPlanStrategy {

    public abstract Double calculatePrice(VehiclePaymentPlan vehiclePaymentPlan);
}
