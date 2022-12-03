package com.demovehiclepro.repository;

import com.demovehiclepro.data.model.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseUserRepository extends JpaRepository<BaseUser, Long> {

    Optional<BaseUser> findByEmail(String email);
}
