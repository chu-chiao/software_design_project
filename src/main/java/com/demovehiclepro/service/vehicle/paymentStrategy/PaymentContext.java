package com.demovehiclepro.service.vehicle.paymentStrategy;

import com.demovehiclepro.data.model.VehiclePaymentPlan;

public class PaymentContext {
    private PaymentPlanStrategy paymentPlanStrategy;

    public PaymentContext(PaymentPlanStrategy paymentPlanStrategy){
        this.paymentPlanStrategy=paymentPlanStrategy;
    }

    public Double calculatePrice(VehiclePaymentPlan vehiclePaymentPlan){
        return paymentPlanStrategy.calculatePrice(vehiclePaymentPlan);
    }

}
