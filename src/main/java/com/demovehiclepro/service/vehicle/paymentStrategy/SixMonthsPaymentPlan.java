package com.demovehiclepro.service.vehicle.paymentStrategy;

import com.demovehiclepro.data.model.VehiclePaymentPlan;

public class SixMonthsPaymentPlan implements PaymentPlanStrategy{

    @Override
    public Double calculatePrice(Double price, Double rate) {

        return price + (price * rate)/6;
    }
}
