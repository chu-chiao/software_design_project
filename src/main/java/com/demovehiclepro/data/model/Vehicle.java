package com.demovehiclepro.data.model;

import com.demovehiclepro.data.enums.Color;
import com.demovehiclepro.data.enums.PaymentPlan;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private Color color;
    private Double price;
    private Integer capacity;
    @OneToMany
    private Set<VehiclePaymentPlan> vehiclePaymentPlans;


}
