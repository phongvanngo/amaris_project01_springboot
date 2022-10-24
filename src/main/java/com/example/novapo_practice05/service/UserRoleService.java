package com.example.novapo_practice05.service;

import com.example.novapo_practice05.domain.UserRole;
import com.example.novapo_practice05.repository.UserRepository;
import com.example.novapo_practice05.repository.UserRoleRepository;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

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
