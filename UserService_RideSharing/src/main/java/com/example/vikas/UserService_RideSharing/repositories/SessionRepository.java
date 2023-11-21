package com.example.vikas.UserService_RideSharing.repositories;


import com.example.vikas.UserService_RideSharing.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByTokenAndUser_Id(String token, Long userId);
}
