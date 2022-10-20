package com.example.novapo_practice05.service;

import com.example.novapo_practice05.domain.UserEntity;
import com.example.novapo_practice05.domain.UserEntity.UserRole;
import com.example.novapo_practice05.exception.UserNotFoundException;
import com.example.novapo_practice05.repository.CatalogRepository;
import com.example.novapo_practice05.repository.UserRepository;
import com.example.novapo_practice05.service.dto.User.SignUpDTO;
import com.example.novapo_practice05.service.dto.User.UserResponseDTO;
import com.example.novapo_practice05.service.mapper.UserMapper;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDTO createUser(SignUpDTO userData) {
        System.out.println(userData.toString());
        UserEntity newUser = userMapper.toEntity(userData);
        newUser.setRole(UserRole.CUSTOMER);
        newUser.setCreatedAt(Instant.now());
        newUser = userRepository.save(userMapper.toEntity(userData));
        System.out.println(newUser.getCreatedAt().toString());
        return userMapper.toResponseDto(newUser);
    }

    public List<UserResponseDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<UserResponseDTO> results = new ArrayList<UserResponseDTO>();

        System.out.println(users);

        for (UserEntity user:users) {
            results.add(userMapper.toResponseDto(user));
        }

        return results;
    }

    public UserEntity getByID(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        }
        else throw new UserNotFoundException(id);
    }

 }
