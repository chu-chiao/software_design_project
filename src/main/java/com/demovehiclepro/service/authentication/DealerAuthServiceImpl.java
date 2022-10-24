package com.demovehiclepro.service.authentication;

import com.demovehiclepro.data.model.BaseUser;
import com.demovehiclepro.data.model.Dealer;
import com.demovehiclepro.data.repository.DealerRepository;
import com.demovehiclepro.dtos.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealerAuthServiceImpl implements AuthService{

    @Autowired
    DealerRepository dealerRepository;

    @Override
    public BaseUser register(RegistrationDTO registrationDTO) {
        Dealer newDealer = new Dealer();

        newDealer.setName(registrationDTO.getName());
        newDealer.setEmail(registrationDTO.getEmail());

        return dealerRepository.save(newDealer);
    }
}
