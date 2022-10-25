package com.example.novapo_practice05.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.novapo_practice05.domain.UserRole;
import com.example.novapo_practice05.repository.UserRoleRepository;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;


    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    public Optional<UserRole> getRoleByName(String name) {
        return userRoleRepository.findByName(name);
    }

}
