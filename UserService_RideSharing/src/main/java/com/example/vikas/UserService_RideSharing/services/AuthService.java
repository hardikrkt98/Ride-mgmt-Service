package com.example.vikas.UserService_RideSharing.services;


import com.example.vikas.UserService_RideSharing.dtos.UserDto;
import com.example.vikas.UserService_RideSharing.models.Role;
import com.example.vikas.UserService_RideSharing.models.Session;
import com.example.vikas.UserService_RideSharing.models.SessionStatus;
import com.example.vikas.UserService_RideSharing.models.User;
import com.example.vikas.UserService_RideSharing.repositories.SessionRepository;
import com.example.vikas.UserService_RideSharing.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.MacAlgorithm;

import java.util.Optional;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.PostMapping;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository ,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity<UserDto> login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty())
        {
            return null;
           // throw new RuntimeException("Wrong username password");
        }

        User user = userOptional.get();

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Wrong username password");
        }


        MacAlgorithm alg = Jwts.SIG.HS256;
        SecretKey key = alg.key().build();

        Map<String, Object>  jsonForJwt = new HashMap<>();
        jsonForJwt.put("email", user.getEmail());
        jsonForJwt.put("roles", user.getRoles());
        jsonForJwt.put("createdAt", new Date());
        jsonForJwt.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));


        String token = Jwts.builder()
                .claims(jsonForJwt)
                .signWith(key, alg)
                .compact();

        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        sessionRepository.save(session);

        UserDto userDto = UserDto.from(user);

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:" + token);

        ResponseEntity<UserDto> response = new ResponseEntity<>(userDto, headers, HttpStatus.OK);

        return response;
    }

    public ResponseEntity<Void> logout(String token, Long userId) {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);

        if (sessionOptional.isEmpty()) {
            return null;
        }

        Session session = sessionOptional.get();

        session.setSessionStatus(SessionStatus.ENDED);

        sessionRepository.save(session);

        return ResponseEntity.ok().build();
    }

    public UserDto signUp(String email, String password) {

        if(userRepository.findByEmail(email).isEmpty()) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            User savedUser = userRepository.save(user);
            return UserDto.from(savedUser);
        }
        else
            throw new RuntimeException("User already exists");
    }

    public SessionStatus validate(String token, Long userId) {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);

        if (sessionOptional.isEmpty()) {
            return SessionStatus.ENDED;
        }

        Session session = sessionOptional.get();

        if (!session.getSessionStatus().equals(SessionStatus.ACTIVE)) {
            return SessionStatus.ENDED;
        }


        Jws<Claims> claimsJws = Jwts.parser()
                .build()
                .parseSignedClaims(token);

        String email = (String) claimsJws.getPayload().get("email");
        List<Role> roles = (List<Role>) claimsJws.getPayload().get("roles");
        Date createdAt = (Date) claimsJws.getPayload().get("createdAt");

        if (createdAt.before(new Date())) {
            return SessionStatus.ENDED;
        }

        return SessionStatus.ACTIVE;
    }

}

