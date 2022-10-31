package com.demovehiclepro.service.authentication;

import com.demovehiclepro.data.model.BaseUser;
import com.demovehiclepro.data.repository.CustomerBookingRepository;
import com.demovehiclepro.dtos.CustomerBookingDTO;
import com.demovehiclepro.dtos.RegistrationDTO;
import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.exceptions.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerBookingAuthService implements AuthService{

    @Autowired
    CustomerBookingRepository customerBookingRepository;
    @Override
    public BaseUser register(RegistrationDTO registrationDTO) {
        var customerBookingDTO=(CustomerBookingDTO)registrationDTO;
        var model=customerBookingDTO.getModel();
        var email = customerBookingDTO.getEmail();
        var customerBooking=customerBookingRepository.findByModelAndEmail(model,email);
        if(customerBooking.isPresent())
        {
            throw new RegistrationException("The Booking already exists");
        }
        CustomerBooking newCustomerBooking=new CustomerBooking();
        newCustomerBooking.setModel(model);
        newCustomerBooking.setLocation(customerBookingDTO.getLocation());
        newCustomerBooking.setName(customerBookingDTO.getName());
        newCustomerBooking.setEmail(email);
        return customerBookingRepository.save(newCustomerBooking);
    }
}
