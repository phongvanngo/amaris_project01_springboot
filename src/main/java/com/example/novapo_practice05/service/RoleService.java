package com.example.novapo_practice05.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.novapo_practice05.domain.Role;
import com.example.novapo_practice05.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;


    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }


}
