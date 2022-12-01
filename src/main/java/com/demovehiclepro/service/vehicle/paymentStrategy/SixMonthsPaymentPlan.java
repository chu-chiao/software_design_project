package com.demovehiclepro.service.vehicle.paymentStrategy;


public class SixMonthsPaymentPlan implements PaymentPlanStrategy{

    @Override
    public Double calculatePrice(Double price, Double interestRate) {

        return price + (price * interestRate)/6;
    }
}
