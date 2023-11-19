package com.example.vikas.UserService_RideSharing.security;

import com.example.vikas.UserService_RideSharing.models.User;
import com.example.vikas.UserService_RideSharing.repositories.UserRepository;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional=userRepository.findByEmail(username);
        if(userOptional.isEmpty())
        {
            throw new UsernameNotFoundException("User dont exist");
        }

        User user =userOptional.get();

        return new CustomUserDetails(user);
    }
}
