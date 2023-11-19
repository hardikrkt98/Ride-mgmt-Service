package com.example.vikas.UserService_RideSharing.repositories;


import com.example.vikas.UserService_RideSharing.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}

