package com.demovehiclepro.dtos;

import com.demovehiclepro.data.enums.Color;
import com.demovehiclepro.data.enums.PaymentPlan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class NewVehicleDTO {
    private String model;
    private Color color;
    private Double price;
    private Integer capacity;
    private PaymentPlan paymentPlan;
}
