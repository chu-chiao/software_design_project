package com.demovehiclepro.repository;

import com.demovehiclepro.data.model.CustomerBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CustomerBookingRepository extends JpaRepository<CustomerBooking,Long> {
    Optional<CustomerBooking> findByEmail(String email);
    Optional<CustomerBooking> findTopByOrderByIdDesc();
    List<CustomerBooking> findAllByDateBetween(Date creationTimeStart, Date creationTimeEnd);
    Optional<CustomerBooking> findByIdAndSalesExecutiveId(long id, long salesExecId);
}
