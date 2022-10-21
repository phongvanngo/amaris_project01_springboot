package com.example.novapo_practice05.repository;

import com.example.novapo_practice05.domain.UserRole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByName(String name);
}
