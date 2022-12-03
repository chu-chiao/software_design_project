package com.demovehiclepro.service.vehicle.paymentStrategy;

public class FourMonthsPaymentPlan implements PaymentPlanStrategy{

    @Override
    public Double calculatePrice(Double price, Double interestRate) {

        return price + (price * interestRate)/4;
    }
}
