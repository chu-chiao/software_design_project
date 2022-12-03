package com.demovehiclepro.service.booking.Notification;

import java.util.Date;

public class NotificationHandler implements INotificationHandler{
    @Override
    public void sendPaymentDoneNotification(Long seId,Long vehId) {
        System.out.printf("Payment for the model {0} has been successful. Contact {1} for further assistance.%n", vehId, seId);
    }
    @Override
    public void sendTestDriveBookedNotification(Long id,Long vehId, Date bookedDate) {
        System.out.printf("Test drive for the model {0} has been booked on {1}. Your booking id is {2}.%n", vehId,bookedDate, id);
    }
    @Override
    public void sendTestDriveOverdueNotification(Long id,Long vehId) {
        System.out.printf("Test drive booking {0} for the model {1} is overdue. Would you like a new date?.%n", id,vehId);
    }
    @Override
    public void sendTestDriveTakenNotification(Long id, Date testDriveDate) {
        System.out.printf("The test drive booking {0} on {1} is successfully taken. Please provide you feedback.%n", id, testDriveDate);
    }
    @Override
    public void sendVehBookingCancelledNotification(Long vehId, String location) {
        System.out.printf("The booking for the model {0} is not available at dealership {1}.%n", vehId, location);
    }
    @Override
    public void sendVehicleBookedNotification(Long vehId, Date vehBookedDate) {
        System.out.printf("Your booking for the model {0} has been confirmed on {1}.%n", vehId, vehBookedDate);
    }

    @Override
    public void callBackPaymentDoneNotification(Long vehId, Long seId) {
        System.out.printf("There was an error in payment for model {0}. Mail called back by {1}.%n", vehId, seId);
    }
    @Override
    public void callBackTestDriveBookedNotification(Long id, Long vehId) {
        System.out.printf("The test drive booked{0} has been cancelled due to unavailability of model {1}. Mail called back%n", id, vehId);
    }
    @Override
    public void callBackTestDriveOverdueNotification(Long id) {System.out.printf("Test drive {0} overdue mail sent needs to be called back%n", id);
    }
    @Override
    public void callBackTestDriveTakenNotification(Long id, Date testDriveDate) {System.out.printf("Test drive {0} on {1} not taken. Mail called back%n", id, testDriveDate);
    }
    @Override
    public void callBackVehBookingCancelledNotification(Long vehId, String location) {System.out.printf("Vehicle{0} booked is back in stock at the dealership {1}. Mail is called back%n", vehId, location);
    }
    @Override
    public void callBackVehBookedNotification(Long vehId) {System.out.printf("Vehicle model {0} is not in stock. So mail is called back%n", vehId);
    }
}
