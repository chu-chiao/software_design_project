package com.demovehiclepro.service.booking.Notification;

import com.demovehiclepro.data.model.CustomerBooking;

public class NotificationHandler {

    public void sendTestDriveBookedNotification(CustomerBooking customerBooking)
    {
        System.out.printf(
                "Test drive is booked on {0}. Booking Id is:{1}%n",customerBooking.getDate(),customerBooking.getId());
    }
    public void sendTestDriveTakenNotification(CustomerBooking customerBooking)
    {
        System.out.printf(
                "Test drive is successfully taken on {0}%n",customerBooking.getDate());
    }
    public void sendTestDriveOverdueNotification(CustomerBooking customerBooking)
    {
        System.out.printf(
                "Test drive booked on is overdue {0}%n",customerBooking.getDate());
    }
    public void sendVehicleBookedNotification(CustomerBooking customerBooking)
    {
        System.out.printf(
                "Your Vehicle {0} is successfully booked on {1}%n",customerBooking.getVehicleId(), customerBooking.getDate());
    }
    public void sendVehBookingCancelledNotification(CustomerBooking customerBooking)
    {
        System.out.printf(
                "Your Vehicle {0} Booked on {1} is cancelled%n",customerBooking.getVehicleId(),customerBooking.getDate());
    }
    public void sendPaymentDoneNotification(CustomerBooking customerBooking)
    {
        System.out.printf(
                "Payment for Vehicle {0} is successful%n",customerBooking.getVehicleId());
    }

    public void unSendTestDriveBookedNotification(CustomerBooking customerBooking)
    {
        System.out.printf(
                "Test drive booked mail sent for the model {0} is called back%n",customerBooking.getVehicleId());
    }

    public void unSendTestDriveTakenNotification(CustomerBooking customerBooking)
    {
        System.out.printf(
                "Test drive taken mail sent for model {0}, is called back%n",customerBooking.getVehicleId());
    }
    public void unSendTestDriveOverdueNotification(CustomerBooking customerBooking)
    {
        System.out.printf(
                "Test drive overdue notification sent for model {0}, is called back%n",customerBooking.getVehicleId());
    }
    public void unSendVehicleBookedNotification(CustomerBooking customerBooking)
    {
        System.out.printf(
                "Vehicle booked mail sent for model {0}, is called back%n",customerBooking.getVehicleId());
    }
    public void unSendVehBookingCancelledNotification(CustomerBooking customerBooking)
    {
        System.out.printf(
                "Vehicle booking cancelled mail sent for model {0}, is called back%n",customerBooking.getVehicleId());
    }
    public void unSendPaymentDoneNotification(CustomerBooking customerBooking)
    {
        System.out.printf(
                "Payment Acknowledgement notification sent {0}, is called back",customerBooking.getVehicleId());
    }
}
