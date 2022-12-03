package com.demovehiclepro.service.authentication;

import com.demovehiclepro.data.model.BaseUser;
import com.demovehiclepro.data.model.Dealer;
import com.demovehiclepro.repository.BaseUserRepository;
import com.demovehiclepro.repository.DealerRepository;
import com.demovehiclepro.data.dtos.RegistrationDTO;
import com.demovehiclepro.exceptions.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DealerAuthService implements AuthService<Dealer>{

    @Autowired
    DealerRepository dealerRepository;

    @Autowired
    BaseUserRepository baseUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Dealer register(RegistrationDTO registrationDTO) {

        Optional<BaseUser> optionalBaseUser =   baseUserRepository.findByEmail(registrationDTO.getEmail());
        if(optionalBaseUser.isPresent()){
            throw new RegistrationException("Registration Failed! User already exists");
        }

        Dealer newDealer = new Dealer();

        BaseUser baseuser = BaseUser.builder()
                .email(registrationDTO.getEmail())
                .name(registrationDTO.getName())
                .password(passwordEncoder.encode(registrationDTO.getPassword()))
                 .userType(registrationDTO.getUserType()).build();
        newDealer.setBaseUser(baseuser);

        return dealerRepository.save(newDealer);
    }
}
