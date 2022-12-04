package com.demovehiclepro.service.booking;

import com.demovehiclepro.data.enums.BookingStatus;
import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.repository.CustomerBookingRepository;
import com.demovehiclepro.exceptions.RegistrationException;
import com.demovehiclepro.service.booking.Notification.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookingService {

    @Autowired
    CustomerBookingRepository customerBookingRepository;
    NotificationService notificationServiceValue;
    public CustomerBooking updateBooking(long salesExecId, long bookingId, BookingStatus bookingStatus){
        var customerBooking=
                customerBookingRepository.findByIdAndSalesExecutiveId(bookingId,salesExecId);
        NotificationService notificationService = getNotificationService();
        CustomerBooking customerBookingData = null;
        if(customerBooking.isEmpty())
        {
            throw new RegistrationException("Booking is not found");
        }
        try {
            customerBookingData=customerBooking.get();
            customerBookingData.setBookingStatus(bookingStatus);
            var currentLeadScore=customerBookingData.getLeadScore();
            var bookingState = BookingStateFactory.createBookingState(customerBookingData);
            customerBookingData.setLeadScore(bookingState.calculateLeadScore(currentLeadScore));
            customerBookingData.setDate(new Date());

            var notificationCommand =
                    NotificationStateFactory.createNotificationCommand(bookingStatus,customerBookingData);
            notificationService.setCommand(notificationCommand);
            notificationService.executeSend();

            customerBookingRepository.save(customerBookingData);
        }
        catch (IllegalArgumentException exception){
            notificationService.executeCallback();
            customerBookingData=null;
        }
        return customerBookingData;
    }
    private NotificationService getNotificationService()
    {
        if (notificationServiceValue == null)
            notificationServiceValue = new NotificationService();
        return notificationServiceValue;
    }
}
