package com.demovehiclepro.controller;

import com.demovehiclepro.dtos.RegistrationDTO;
import com.demovehiclepro.util.FactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("auth")
public class Authentication{
    @Autowired
    FactoryUtil factoryUtil;

    @PostMapping("/v1/register")
    public ResponseEntity<?> register(@RequestBody RegistrationDTO registrationDTO){

        return ResponseEntity.ok(factoryUtil.register(registrationDTO));
    }

}
