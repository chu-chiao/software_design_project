package com.demovehiclepro.service.booking.Notification;

import java.util.Date;

public interface INotificationHandler {
    void sendPaymentDoneNotification(Long seId,Long vehId);
    void sendTestDriveBookedNotification(Long id,Long vehId, Date bookedDate);
    void sendTestDriveOverdueNotification(Long id,Long vehId);
    void sendTestDriveTakenNotification(Long id, Date testDriveDate);
    void sendVehBookingCancelledNotification(Long vehId, String location);
    void sendVehicleBookedNotification(Long vehId, Date vehBookedDate);
    void callBackPaymentDoneNotification(Long vehId, Long seId);
    void callBackTestDriveBookedNotification(Long id, Long vehId);
    void callBackTestDriveOverdueNotification(Long id);
    void callBackTestDriveTakenNotification(Long id, Date testDriveDate);
    void callBackVehBookingCancelledNotification(Long vehId, String location);
    void callBackVehBookedNotification(Long vehId);
}
