package com.demovehiclepro.data.dtos;

import com.demovehiclepro.data.enums.Color;
import com.demovehiclepro.data.enums.PaymentPlan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Setter
@Getter
public class NewVehicleDTO {
    private String model;
    private Color color;
    private Double price;
    private Integer capacity;
    private Set<PaymentPlan> paymentPlan;
}
