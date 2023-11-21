package com.ridesharing.drivermanagement.repositories;

import com.ridesharing.drivermanagement.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Optional<Driver> findByEmail(String email);
}
