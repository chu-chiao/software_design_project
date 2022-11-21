package com.demovehiclepro.service.booking;

public class VehicleBookingCancelled extends BookingState{

    @Override
    public int CalculateLeadScore(int leadScore) {
        leadScore=leadScore-40;
        return leadScore;
    }
}
