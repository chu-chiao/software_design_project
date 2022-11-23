package com.demovehiclepro.data.model;

import com.demovehiclepro.data.enums.PaymentPlan;
import com.demovehiclepro.service.vehicle.paymentStrategy.PaymentPlanStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VehiclePaymentPlan{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double price;
    private Double rate;

    private Double priceToPay;

    private PaymentPlan paymentPlanName;

    public VehiclePaymentPlan(Double price) {
        this.price = price;
    }

    public Double calculatePriceToPay(PaymentPlanStrategy paymentPlanStrategy) {
        return paymentPlanStrategy.calculatePrice(this.price, this.rate);
    }
}
