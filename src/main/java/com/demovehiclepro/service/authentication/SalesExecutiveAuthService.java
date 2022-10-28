package com.demovehiclepro.service.authentication;

import com.demovehiclepro.data.model.BaseUser;
import com.demovehiclepro.data.model.SalesExecutive;
import com.demovehiclepro.data.repository.SalesExecutiveRepository;
import com.demovehiclepro.dtos.RegistrationDTO;
import com.demovehiclepro.exceptions.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesExecutiveAuthService implements AuthService{

    @Autowired
    SalesExecutiveRepository salesExecutiveRepository;

    @Override
    public BaseUser register(RegistrationDTO registrationDTO) {
        var getSalesExecutive=salesExecutiveRepository.findByEmail(registrationDTO.getEmail());
        if (getSalesExecutive.isPresent())
        {
            throw new RegistrationException("Registration Failed! User already exists");
        }
        var salesExecutive=new SalesExecutive();
        salesExecutive.setName(registrationDTO.getName());
        salesExecutive.setEmail(registrationDTO.getEmail());
        return salesExecutiveRepository.save(salesExecutive);
    }
}
