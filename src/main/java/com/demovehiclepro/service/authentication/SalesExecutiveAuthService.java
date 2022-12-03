package com.demovehiclepro.service.authentication;

import com.demovehiclepro.data.model.BaseUser;
import com.demovehiclepro.data.model.SalesExecutive;
import com.demovehiclepro.repository.SalesExecutiveRepository;
import com.demovehiclepro.data.dtos.RegistrationDTO;
import com.demovehiclepro.exceptions.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SalesExecutiveAuthService implements AuthService{

    @Autowired
    SalesExecutiveRepository salesExecutiveRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public SalesExecutive register(RegistrationDTO registrationDTO) {

        var getSalesExecutive=salesExecutiveRepository.findByEmail(registrationDTO.getEmail());

        if (getSalesExecutive.isPresent())
        {
            throw new RegistrationException("Registration Failed! User already exists");
        }

        var salesExecutive = new SalesExecutive();

        BaseUser baseuser = BaseUser.builder()
                .email(registrationDTO.getEmail())
                .name(registrationDTO.getName())
                .password(passwordEncoder.encode(registrationDTO.getPassword()))
                .userType(registrationDTO.getUserType()).build();
        salesExecutive.setBaseUser(baseuser);

        return salesExecutiveRepository.save(salesExecutive);
    }
}
