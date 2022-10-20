package com.example.novapo_practice05.repository;

import com.example.novapo_practice05.domain.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
//    List<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByEmail(String email);


}
