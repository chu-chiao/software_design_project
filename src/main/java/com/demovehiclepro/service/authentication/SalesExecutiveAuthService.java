package com.demovehiclepro.service.authentication;

import com.demovehiclepro.data.model.BaseUser;
import com.demovehiclepro.data.model.SalesExecutive;
import com.demovehiclepro.repository.BaseUserRepository;
import com.demovehiclepro.repository.SalesExecutiveRepository;
import com.demovehiclepro.data.dtos.RegistrationDTO;
import com.demovehiclepro.exceptions.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalesExecutiveAuthService implements AuthService{

    @Autowired
    SalesExecutiveRepository salesExecutiveRepository;

    @Autowired
    BaseUserRepository baseUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ROLE_DEALER')")
    @Override
    public SalesExecutive register(RegistrationDTO registrationDTO) {

        Optional<BaseUser> optionalBaseUser =   baseUserRepository.findByEmail(registrationDTO.getEmail());

        if (optionalBaseUser.isPresent())
        {
            throw new RegistrationException("Registration Failed! User already exists");
        }

        var salesExecutive = new SalesExecutive();

        BaseUser baseuser = BaseUser.builder()
                .email(registrationDTO.getEmail())
                .name(registrationDTO.getName())
                .password(passwordEncoder.encode(registrationDTO.getPassword()))
                .userType(registrationDTO.getUserType())
                .enabled(true).build();
        salesExecutive.setBaseUser(baseuser);

        return salesExecutiveRepository.save(salesExecutive);
    }
}
