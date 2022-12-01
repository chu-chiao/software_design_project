package com.demovehiclepro.controller;

import com.demovehiclepro.data.model.BaseUser;
import com.demovehiclepro.dtos.RegistrationDTO;
import com.demovehiclepro.service.authentication.AuthService;
import com.demovehiclepro.util.AuthServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController{
    @Autowired
    AuthServiceFactory authServiceFactory;

    @PostMapping("/v1/register")
    public ResponseEntity<BaseUser> register(@RequestBody RegistrationDTO registrationDTO){

        AuthService newAuthService = authServiceFactory.createAuthService(registrationDTO);

        return ResponseEntity.ok(newAuthService.register(registrationDTO));
    }

}
