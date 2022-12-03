package com.demovehiclepro.service.authentication;

import com.demovehiclepro.data.enums.BookingStatus;
import com.demovehiclepro.repository.CustomerBookingRepository;
import com.demovehiclepro.repository.SalesExecutiveRepository;
import com.demovehiclepro.data.dtos.CustomerBookingDTO;
import com.demovehiclepro.data.dtos.RegistrationDTO;
import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.exceptions.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerBookingAuthService implements AuthService{

    @Autowired
    CustomerBookingRepository customerBookingRepository;


    @Autowired
    SalesExecutiveRepository salesExecutiveRepository;

    @Override
    public CustomerBooking register(RegistrationDTO registrationDTO) {
        var customerBookingDTO=(CustomerBookingDTO)registrationDTO;
        var email = customerBookingDTO.getEmail();
        var customerBooking=
                //Customer booking restricted to one at the moment
                customerBookingRepository.findByEmail(email);

        // Pre-condition: Customer Booking should not exist
        if(customerBooking.isPresent())
        {
            throw new RegistrationException("The Booking already exists");
        }
        long count = customerBookingRepository.count();
        CustomerBooking newCustomerBooking=getCustomerBooking(customerBookingDTO);
        CustomerBooking savedCustomerBooking = customerBookingRepository.save(newCustomerBooking);

        // Post-condition: Customer booking created
        assert(savedCustomerBooking.getId()!=null);

        assert customerBookingRepository.count() == count + 1;

        return savedCustomerBooking;
    }

    private CustomerBooking getCustomerBooking(CustomerBookingDTO customerBookingDTO)
    {
        var newCustomerBooking=new CustomerBooking();
        newCustomerBooking.setVehicleId(customerBookingDTO.getVehicleId());
        newCustomerBooking.setLocation(customerBookingDTO.getLocation());
        newCustomerBooking.setName(customerBookingDTO.getName());
        newCustomerBooking.setEmail(customerBookingDTO.getEmail());
        newCustomerBooking.setDate(customerBookingDTO.getDate());
        newCustomerBooking.setBookingStatus(BookingStatus.TEST_DRIVE_BOOKED);
        newCustomerBooking.setLeadScore(10);
        newCustomerBooking.setSalesExecutiveId(setAssignee());
        return newCustomerBooking;
    }

    private Long setAssignee()
    {
        var listOfSEs=salesExecutiveRepository.findAll();
        if(listOfSEs.isEmpty())
        {
            throw new RegistrationException("No Sales Executives are registered");
        }

        var lastCreatedBooking=customerBookingRepository.findTopByOrderByIdDesc();
        Long seId;
        if(lastCreatedBooking.isPresent() && (long) listOfSEs.size() > 1)
        {
            //gets the id of the last assigned sales executive for a booking
            seId=lastCreatedBooking.get().getSalesExecutiveId();

            //gets the sales executive id which is other than the last assigned one. Randomly assigned
            Long finalSeId = seId;
            var nextAssignedSE=listOfSEs.stream().filter(se->!se.getId().equals(finalSeId)).findFirst();
           if(nextAssignedSE.isPresent())
           {
               seId=nextAssignedSE.get().getId();
           }
        }
        else
        {
            //if the booking is the very first one
            seId=listOfSEs.get(0).getId();
        }

        return seId;
    }
}
