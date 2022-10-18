package com.example.novapo_practice05.repository;

import com.example.novapo_practice05.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

}
