package com.demovehiclepro.repository;

import com.demovehiclepro.data.model.SalesExecutive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalesExecutiveRepository extends JpaRepository<SalesExecutive,Long> {
    Optional<SalesExecutive> findByEmail(String email);
}
