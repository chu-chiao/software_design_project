package com.demovehiclepro.service.vehicle.paymentStrategy;

public class OneOffPaymentPlan implements PaymentPlanStrategy{

    @Override
    public Double calculatePrice(Double price, Double interestRate) {
        return price;
    }
}
