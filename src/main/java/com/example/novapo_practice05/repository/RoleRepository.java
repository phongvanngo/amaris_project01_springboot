package com.example.novapo_practice05.repository;

import com.example.novapo_practice05.domain.Role;
import com.example.novapo_practice05.repository.CustomRepository.RoleCustomRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long>, RoleCustomRepository {
    Optional<Role> findByName(String name);

//    @Query(value="SELECT roles.id, roles.name FROM users_roles as ur "
//        + "INNER JOIN "
//        + "roles ON roles.id = ur.role_id "
//        + "WHERE ur.user_id = :id",nativeQuery = true)
//    List<Role> findRolesByUserID(long id);
}
