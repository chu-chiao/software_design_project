package com.demovehiclepro.service.booking.Notification;

import java.util.Date;

public class NotificationHandler implements INotificationHandler{
    @Override
    public void sendPaymentDoneNotification(Long seId,Long vehId) {
        System.out.printf("Payment for the model %s has been successful. Contact %s for further assistance.%n", vehId, seId);
    }
    @Override
    public void sendTestDriveBookedNotification(Long id,Long vehId, Date bookedDate) {
        System.out.printf("Test drive for the model %s has been booked on %s. Your booking id is %s.%n", vehId,bookedDate, id);
    }
    @Override
    public void sendTestDriveOverdueNotification(Long id,Long vehId) {
        System.out.printf("Test drive booking %s for the model %s is overdue. Would you like a new date?.%n", id,vehId);
    }
    @Override
    public void sendTestDriveTakenNotification(Long id, Date testDriveDate) {
        System.out.printf("The test drive booking %s on %s is successfully taken. Please provide you feedback.%n", id, testDriveDate);
    }
    @Override
    public void sendVehBookingCancelledNotification(Long vehId, String location) {
        System.out.printf("The booking for the model %s is not available at dealership %s.%n", vehId, location);
    }
    @Override
    public void sendVehicleBookedNotification(Long vehId, Date vehBookedDate) {
        System.out.printf("Your booking for the model %s has been confirmed on %s.%n", vehId, vehBookedDate);
    }
    @Override
    public void callBackPaymentDoneNotification(Long vehId, Long seId) {
        System.out.printf("There was an error in payment for model %s. Mail called back by %s.%n", vehId, seId);
    }
    @Override
    public void callBackTestDriveBookedNotification(Long id, Long vehId) {
        System.out.printf("The test drive booked %s has been cancelled due to unavailability of model %s. Mail called back%n", id, vehId);
    }
    @Override
    public void callBackTestDriveOverdueNotification(Long id) {System.out.printf("Test drive %s overdue mail sent needs to be called back%n", id);
    }
    @Override
    public void callBackTestDriveTakenNotification(Long id, Date testDriveDate) {System.out.printf("Test drive %s on %s not taken. Mail called back%n", id, testDriveDate);
    }
    @Override
    public void callBackVehBookingCancelledNotification(Long vehId, String location) {System.out.printf("Vehicle %s booked is back in stock at the dealership %s. Mail is called back%n", vehId, location);
    }
    @Override
    public void callBackVehBookedNotification(Long vehId) {System.out.printf("Vehicle model %s is not in stock. So mail is called back%n", vehId);
    }
}
