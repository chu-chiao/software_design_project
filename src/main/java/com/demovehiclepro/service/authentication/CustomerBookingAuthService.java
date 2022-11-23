package com.demovehiclepro.service.authentication;

import com.demovehiclepro.data.enums.BookingStatus;
import com.demovehiclepro.data.model.BaseUser;
import com.demovehiclepro.data.repository.CustomerBookingRepository;
import com.demovehiclepro.data.repository.SalesExecutiveRepository;
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

    @Autowired
    SalesExecutiveRepository salesExecutiveRepository;
    @Override
    public BaseUser register(RegistrationDTO registrationDTO) {
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
        CustomerBooking newCustomerBooking=GetCustomerBooking(customerBookingDTO);
        CustomerBooking savedCustomerBooking = customerBookingRepository.save(newCustomerBooking);

        // Post-condition: Customer booking created
        assert(savedCustomerBooking.getId()!=null);

        assert customerBookingRepository.count() == count + 1;

        return savedCustomerBooking;
    }

    private CustomerBooking GetCustomerBooking(CustomerBookingDTO customerBookingDTO)
    {
        var newCustomerBooking=new CustomerBooking();
        newCustomerBooking.setVehicleId(customerBookingDTO.getVehicleId());
        newCustomerBooking.setLocation(customerBookingDTO.getLocation());
        newCustomerBooking.setName(customerBookingDTO.getName());
        newCustomerBooking.setEmail(customerBookingDTO.getEmail());
        newCustomerBooking.setDate(customerBookingDTO.getDate());
        newCustomerBooking.setBookingStatus(BookingStatus.TEST_DRIVE_BOOKED);
        newCustomerBooking.setLeadScore(10);
        newCustomerBooking.setSalesExecutiveId(SetAssignee());
        return newCustomerBooking;
    }

    private Long SetAssignee()
    {
        var listOfSEs=salesExecutiveRepository.findAll();
        if(listOfSEs.isEmpty())
        {
            throw new RegistrationException("No Sales Executives are registered");
        }

        var lastCreatedBooking=customerBookingRepository.findTopByOrderByIdDesc();
        Long se_Id;
        if(lastCreatedBooking.isPresent() && (long) listOfSEs.size() > 1)
        {
            //gets the id of the last assigned sales executive for a booking
            se_Id=lastCreatedBooking.get().getSalesExecutiveId();

            //gets the sales executive id which is other than the last assigned one. Randomly assigned
            Long finalSe_Id = se_Id;
            var nextAssignedSE=listOfSEs.stream().filter(se->!se.getId().equals(finalSe_Id)).findFirst();
           if(nextAssignedSE.isPresent())
           {
               se_Id=nextAssignedSE.get().getId();
           }
        }
        else
        {
            //if the booking is the very first one
            se_Id=listOfSEs.get(0).getId();
        }

        return se_Id;
    }
}
