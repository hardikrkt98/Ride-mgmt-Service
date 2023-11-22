package com.ridesharing.drivermanagement.controllers;

import com.ridesharing.drivermanagement.dtos.AuthenticationResponse;
import com.ridesharing.drivermanagement.dtos.LoginRequest;
import com.ridesharing.drivermanagement.dtos.RegisterRequest;
import com.ridesharing.drivermanagement.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest request
    ) {
        AuthenticationResponse response = authenticationService.login(request);
        System.out.println(response.toString());
        System.out.println(response.getToken());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
