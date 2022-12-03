package com.demovehiclepro.repository;

import com.demovehiclepro.data.model.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DealerRepository extends JpaRepository<Dealer, Long>{
    Optional<Dealer> findByEmail(String email);

    void deleteDealerByEmail(String email);
}
