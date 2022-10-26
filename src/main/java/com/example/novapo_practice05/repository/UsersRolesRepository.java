package com.example.novapo_practice05.repository;

import com.example.novapo_practice05.domain.UsersRoles;
import com.example.novapo_practice05.domain.UsersRolesID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRolesRepository extends JpaRepository<UsersRoles, UsersRolesID> {

    List<UsersRoles> findByUserID(Long userID);
}
