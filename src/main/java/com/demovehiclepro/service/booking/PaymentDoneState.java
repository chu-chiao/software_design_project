package com.demovehiclepro.service.booking;

public class PaymentDoneState extends BookingState{
    @Override
    public int calculateLeadScore(int leadScore) {
        leadScore=leadScore+100;
        return leadScore;
    }
}
