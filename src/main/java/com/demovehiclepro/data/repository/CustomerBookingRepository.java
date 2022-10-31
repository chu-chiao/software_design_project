package com.demovehiclepro.data.repository;

import com.demovehiclepro.data.model.CustomerBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerBookingRepository extends JpaRepository<CustomerBooking,Long> {
    Optional<CustomerBooking> findByModelAndEmail(String model, String email);
}
