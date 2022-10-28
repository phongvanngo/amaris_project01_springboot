package com.example.novapo_practice05.repository.CustomRepository;

import com.example.novapo_practice05.domain.Role;
import java.util.List;

public interface RoleCustomRepository {
    List<Role> findRolesByUserID(Long id);
}
