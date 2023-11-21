package com.example.vikas.UserService_RideSharing.services;


import com.example.vikas.UserService_RideSharing.models.Role;
import com.example.vikas.UserService_RideSharing.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(String name) {
        Role role = new Role();
        role.setRole(name);

        return roleRepository.save(role);
    }
}
