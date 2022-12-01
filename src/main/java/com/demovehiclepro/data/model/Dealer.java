package com.demovehiclepro.data.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Dealer extends BaseUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private Set<SalesExecutive> salesExecutives;

    @OneToMany
    private Set<Vehicle> vehicles;

}
