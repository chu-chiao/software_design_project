package com.demovehiclepro.service.booking;

import java.util.Date;

public abstract class BookingState {
    abstract int calculateLeadScore(int leadScore);
    protected int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
