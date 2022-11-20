package com.demovehiclepro.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehiclePaymentPlan extends VehiclePayment{

    private Integer id;

    private Integer vehicleId;
    private Double price;

    private Double priceToPay;

    private String paymentPlan;

    public VehiclePaymentPlan(Integer vehicleId, Double price) {
        this.vehicleId = vehicleId;
        this.price = price;
    }

    @Override
    public Double calculatePrice() {
        return price;
    }
}
