package com.demovehiclepro.data.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private Set<SalesExecutive> salesExecutives;

    @OneToMany
    private Set<Vehicle> vehicles;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BaseUser baseUser;
}
