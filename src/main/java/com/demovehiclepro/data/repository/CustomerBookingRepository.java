package com.demovehiclepro.data.repository;

import com.demovehiclepro.data.model.CustomerBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CustomerBookingRepository extends JpaRepository<CustomerBooking,Long> {
    //Optional<CustomerBooking> findByVehicleIdAndEmailAndName(Long vehicleId, String email, String name);
    Optional<CustomerBooking> findByEmail(String email);
    Optional<CustomerBooking> findTopByOrderByIdDesc();
    List<CustomerBooking> findAllByCreationDateBetween(Date creationTimeStart, Date creationTimeEnd);
}
