package com.demovehiclepro.service.booking;

import com.demovehiclepro.data.enums.BookingStatus;
import com.demovehiclepro.data.model.CustomerBooking;
import com.demovehiclepro.data.repository.CustomerBookingRepository;
import com.demovehiclepro.exceptions.RegistrationException;
import com.demovehiclepro.service.booking.Notification.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookingService {

    @Autowired
    CustomerBookingRepository customerBookingRepository;
    BookingStateFactory bookingStateFactory;
    NotificationStateFactory notificationStateFactory;
    NotificationService notificationService;
    public CustomerBooking updateBooking(long salesExecId, long bookingId, BookingStatus bookingStatus, Date date){
        var customerBooking=
                customerBookingRepository.findByIdAndSalesExecutiveId(bookingId,salesExecId);
        var notificationService = getNotificationService();
        CustomerBooking customer_BookingData =null;
        if(customerBooking.isEmpty())
        {
            throw new RegistrationException("Booking is not found");
        }
        try {
            customer_BookingData=customerBooking.get();
            var currentLeadScore=customer_BookingData.getLeadScore();
            var bookingState = getBookingStateFactory().createBookingState(customer_BookingData);
            customer_BookingData.setLeadScore(bookingState.calculateLeadScore(currentLeadScore));
            customer_BookingData.setBookingStatus(bookingStatus);
            customer_BookingData.setDate(date);

            var notificationCommand =
                    getNotificationStateFactory().createNotificationCommand(bookingStatus,customer_BookingData);
            notificationService.setCommand(notificationCommand);
            notificationService.executeSend();

            customerBookingRepository.save(customer_BookingData);
        }
        catch (IllegalArgumentException exception){
            notificationService.executeCallback();
        }
        return customer_BookingData;
    }

    private BookingStateFactory getBookingStateFactory()
    {
        if(bookingStateFactory == null)
            bookingStateFactory = new BookingStateFactory();
        return bookingStateFactory;
    }
    private NotificationStateFactory getNotificationStateFactory()
    {
        if (notificationStateFactory == null)
            notificationStateFactory = new NotificationStateFactory();
        return notificationStateFactory;
    }
    private NotificationService getNotificationService()
    {
        if (notificationService == null)
            notificationService = new NotificationService();
        return notificationService;
    }
}
