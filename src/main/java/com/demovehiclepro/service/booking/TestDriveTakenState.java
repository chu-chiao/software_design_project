package com.demovehiclepro.service.booking;

public class TestDriveTakenState extends BookingState{
    @Override
    public int calculateLeadScore(int leadScore) {
        leadScore=leadScore+20;
        return leadScore;
    }
}
