package com.demovehiclepro.service.booking;

import java.util.Date;
public class TestDriveOverdueState extends BookingState{
    Date dateOfBooking;
    public TestDriveOverdueState(Date dateOfBooking)
    {
        this.dateOfBooking = dateOfBooking;
    }
    @Override
    public int calculateLeadScore(int leadScore) {
        int days = daysBetween(dateOfBooking, new Date());
        if (days>=10) {
            leadScore=0;
        }
        else{
            leadScore = leadScore-days;
        }
        return leadScore;
    }
}
