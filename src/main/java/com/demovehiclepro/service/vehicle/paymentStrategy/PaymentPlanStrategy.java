package com.demovehiclepro.service.vehicle.paymentStrategy;

public interface PaymentPlanStrategy {

    Double calculatePrice(Double price, Double interestRate);
}
