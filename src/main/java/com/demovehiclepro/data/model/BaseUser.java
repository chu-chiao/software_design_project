package com.demovehiclepro.data.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class BaseUser {
    private String name;
    private String email;
}
