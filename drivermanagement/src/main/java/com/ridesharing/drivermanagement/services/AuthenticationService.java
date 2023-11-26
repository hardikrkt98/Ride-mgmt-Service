package com.ridesharing.drivermanagement.services;

import com.ridesharing.drivermanagement.dtos.AuthenticationResponse;
import com.ridesharing.drivermanagement.dtos.LoginRequest;
import com.ridesharing.drivermanagement.dtos.RegisterRequest;
import com.ridesharing.drivermanagement.models.User;
import com.ridesharing.drivermanagement.role.Role;
import com.ridesharing.drivermanagement.repositories.UserRepository;
import com.ridesharing.drivermanagement.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        System.out.println("register");
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .role(Role.USER)
                .build();
        System.out.println(user.getUsername());
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        System.out.println("login requrest");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(user.getUsername());
        String token = jwtService.generateToken(user);
        System.out.println("token"+token);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
