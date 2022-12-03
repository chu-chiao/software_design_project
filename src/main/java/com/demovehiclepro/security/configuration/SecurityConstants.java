package com.demovehiclepro.security.configuration;

 final public class SecurityConstants {
     protected static final String DEALER_CREATE_EXECUTIVE = "/auth/v1/register"; // dealer
     protected static final String UPDATE_BOOKING = "/auth/v1/updateBooking"; // all user type except dealer can update booking
     protected static final String GENERATE_REPORT = "/report/v1/gen-report"; // sale executive and dealer
     protected static final String ADD_VEHICLE = "/vehicle/v1/add-vehicle"; // dealer
     protected static final String GET_VEHICLES = "/vehicle/v1/vehicle-list"; // all user-type


 }
