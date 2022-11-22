package com.demovehiclepro.data.model;

import com.demovehiclepro.data.enums.PaymentPlan;
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

    private PaymentPlan paymentPlan;

    public VehiclePaymentPlan(Double price) {
        this.price = price;
    }

    public Double calculatePrice() {
        return price;
    }
}
