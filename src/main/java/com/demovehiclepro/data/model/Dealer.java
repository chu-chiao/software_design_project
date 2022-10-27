package com.demovehiclepro.data.model;


import lombok.Data;
import org.apache.tomcat.jni.Address;

import javax.persistence.*;
import java.util.HashSet;
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
