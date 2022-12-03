package com.demovehiclepro.repository;

import com.demovehiclepro.data.enums.Color;
import com.demovehiclepro.data.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByModelAndColor(String model, Color color);
}
