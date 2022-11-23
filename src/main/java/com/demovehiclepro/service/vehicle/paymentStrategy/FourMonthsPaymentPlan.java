package com.demovehiclepro.service.vehicle.paymentStrategy;

import com.demovehiclepro.data.model.VehiclePaymentPlan;

public class FourMonthsPaymentPlan implements PaymentPlanStrategy{

    @Override
    public Double calculatePrice(Double price, Double interestRate) {

        return price + (price * interestRate)/4;
    }
}
