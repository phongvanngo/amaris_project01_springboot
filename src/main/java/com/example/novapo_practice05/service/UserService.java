package com.example.novapo_practice05.service;

import com.example.novapo_practice05.domain.UserEntity;
import com.example.novapo_practice05.domain.UserEntity.UserRole;
import com.example.novapo_practice05.exception.CouldNotCreateUserException;
import com.example.novapo_practice05.exception.DuplicateEmailException;
import com.example.novapo_practice05.exception.UserNotFoundException;
import com.example.novapo_practice05.repository.UserRepository;
import com.example.novapo_practice05.service.dto.User.SignUpDTO;
import com.example.novapo_practice05.service.dto.User.UserResponseDTO;
import com.example.novapo_practice05.service.mapper.UserMapper;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.userMapper = userMapper;
//        this.passwordEncoder = passwordEncoder;
//    }

    public UserResponseDTO createUser(SignUpDTO userData) {
        System.out.println(userData.toString());
        UserEntity newUser = userMapper.toEntity(userData);
        newUser.setRole(UserRole.ROLE_CUSTOMER);
        newUser.setCreatedAt(Instant.now());
        newUser = userRepository.save(userMapper.toEntity(userData));
        System.out.println(newUser.getCreatedAt().toString());
        return userMapper.toResponseDto(newUser);
    }

    public List<UserResponseDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<UserResponseDTO> results = new ArrayList<UserResponseDTO>();

        System.out.println(users);

        for (UserEntity user : users) {
            results.add(userMapper.toResponseDto(user));
        }

        return results;
    }

    public UserEntity getByID(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        String userName, password = null;
//        List<GrantedAuthority> authorities = null;
//        List<UserEntity> user = userRepository.findByEmail(username);
//        if (user.size() == 0) {
//            throw new UsernameNotFoundException("User details not found for the user : " + username);
//        } else {
//            userName = user.get(0).getEmail();
//            password = user.get(0).getPassword();
//            authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(user.get(0).getRole().toString()));
//        }
//        return new User(username, password, authorities);
        return null;
    }

//    private void validateUniqueEmail(String email) throws DuplicateEmailException {
//        List<UserEntity> user = userRepository.findByEmail(email);
//        if (user.size() > 0) {
//            throw new DuplicateEmailException(email);
//        }
//    }
//
//    private boolean isEmailUnique(String email) {
//        List<UserEntity> user = userRepository.findByEmail(email);
//        return user.size() == 0;
//    }

    private boolean isEmailUnique(String email) {
        var user = userRepository.findByEmail(email);
//        System.out.println(user.get());
        return userRepository.findByEmail(email).isEmpty();
    }

    public UserResponseDTO registerUser(SignUpDTO signUpDTO) {
//        validateUniqueEmail(signUpDTO.getEmail());
        if (!isEmailUnique(signUpDTO.getEmail())) {
            throw new DuplicateEmailException(signUpDTO.getEmail());
        }
        UserEntity savedUser = userMapper.toEntity(signUpDTO);
        UserResponseDTO newUser = null;
        try {
            String hashPwd = passwordEncoder.encode(savedUser.getPassword());
            savedUser.setPassword(hashPwd);
            savedUser = userRepository.save(savedUser);
            if (savedUser.getId() > 0) {
                newUser = userMapper.toResponseDto(savedUser);
            }

        } catch (Exception ex) {
            throw new CouldNotCreateUserException(savedUser.getEmail());
        }
        return newUser;
    }

    public UserResponseDTO registerAdminUser(SignUpDTO signUpDTO) {
//        validateUniqueEmail(signUpDTO.getEmail());
        if (!isEmailUnique(signUpDTO.getEmail())) {
            throw new DuplicateEmailException(signUpDTO.getEmail());
        }
        UserEntity savedUser = userMapper.toEntity(signUpDTO);
        UserResponseDTO newUser = null;
        try {
            String hashPwd = passwordEncoder.encode(savedUser.getPassword());
            savedUser.setPassword(hashPwd);
            savedUser.setRole(UserRole.ROLE_ADMINISTRATOR);
            savedUser = userRepository.save(savedUser);
            if (savedUser.getId() > 0) {
                newUser = userMapper.toResponseDto(savedUser);
            }

        } catch (Exception ex) {
            throw new CouldNotCreateUserException(savedUser.getEmail());
        }
        return newUser;
    }

}
