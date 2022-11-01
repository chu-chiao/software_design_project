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

import java.util.Date;

@Service
public class CustomerBookingAuthService implements AuthService{

    @Autowired
    CustomerBookingRepository customerBookingRepository;
    SalesExecutiveRepository salesExecutiveRepository;
    @Override
    public BaseUser register(RegistrationDTO registrationDTO) {
        var customerBookingDTO=(CustomerBookingDTO)registrationDTO;
        var vehicleId=customerBookingDTO.getVehicleId();
        var email = customerBookingDTO.getEmail();
        var customerBooking=customerBookingRepository.findByVehicleIdAndEmail(vehicleId,email);

        if(customerBooking.isPresent())
        {
            throw new RegistrationException("The Booking already exists");
        }
        CustomerBooking newCustomerBooking=GetCustomerBooking(customerBookingDTO);
        return customerBookingRepository.save(newCustomerBooking);
    }

    private CustomerBooking GetCustomerBooking(CustomerBookingDTO customerBookingDTO)
    {
        var newCustomerBooking=new CustomerBooking();
        newCustomerBooking.setVehicleId(customerBookingDTO.getVehicleId());
        newCustomerBooking.setLocation(customerBookingDTO.getLocation());
        newCustomerBooking.setName(customerBookingDTO.getName());
        newCustomerBooking.setEmail(customerBookingDTO.getEmail());
        newCustomerBooking.setBookingStatus(BookingStatus.TEST_DRIVE_BOOKED);
        newCustomerBooking.setCreationDate(new Date());
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

        // Todo Rosmy: to check the below logic
        var lastCreatedBooking=customerBookingRepository.findTopByOrderByIdDesc();
        Long se_Id;
        if(lastCreatedBooking.isPresent() && (long) listOfSEs.size() > 1)
        {
            //gets the id of the last assigned sales executive for a booking
            se_Id=lastCreatedBooking.get().getSalesExecutiveId();

            //gets the sales executive id which is other than the last assigned one.
            //Todo:Rosmy: verify the linq impl
            Long finalSe_Id = se_Id;
            var nextAssignedSE=listOfSEs.stream().filter(se->se.getId().equals(finalSe_Id)).findFirst();
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
