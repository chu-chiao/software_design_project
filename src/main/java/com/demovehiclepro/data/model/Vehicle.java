package com.demovehiclepro.data.model;

import com.demovehiclepro.data.enums.Color;
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

    @Enumerated(EnumType.STRING)
    private Color color;
    private Double price;
    private Integer capacity;

    @OneToMany
    @Enumerated(EnumType.STRING)
    private Set<VehiclePaymentPlan> vehiclePaymentPlans;


}
