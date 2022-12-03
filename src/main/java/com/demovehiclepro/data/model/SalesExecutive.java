package com.demovehiclepro.data.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SalesExecutive{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BaseUser baseUser;
}


