package com.demovehiclepro.service.booking;

import java.util.Date;

public class VehicleBookedState extends BookingState{

    Date dateOfTestDriveTaken;
    public VehicleBookedState(Date dateOfTestDriveTaken)
    {
        this.dateOfTestDriveTaken = dateOfTestDriveTaken;
    }
    @Override
    public int CalculateLeadScore(int leadScore) {

        int days = daysBetween(dateOfTestDriveTaken, new Date());
            if( days<=1) {
                leadScore = leadScore + 50;
            }
            else if(days <= 5) {
                leadScore = leadScore + 30;
            }
            else {
                leadScore = leadScore + 15;
            }
        return leadScore;
    }
}
