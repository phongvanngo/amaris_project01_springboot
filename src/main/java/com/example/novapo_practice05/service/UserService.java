package com.example.novapo_practice05.service;

import com.example.novapo_practice05.domain.CustomUserDetails;
import com.example.novapo_practice05.domain.UserEntity;
import com.example.novapo_practice05.domain.UserRole;
import com.example.novapo_practice05.exception.CouldNotCreateUserException;
import com.example.novapo_practice05.exception.DuplicateEmailException;
import com.example.novapo_practice05.exception.UserNotFoundException;
import com.example.novapo_practice05.exception.UserRoleNotFoundException;
import com.example.novapo_practice05.repository.UserRepository;
import com.example.novapo_practice05.service.dto.User.SetRoleDTO;
import com.example.novapo_practice05.service.dto.User.SignUpDTO;
import com.example.novapo_practice05.service.dto.User.UserResponseDTO;
import com.example.novapo_practice05.service.mapper.UserMapper;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.management.relation.RoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
    private UserRoleService userRoleService;


    @Autowired
    private PasswordEncoder passwordEncoder;

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
        String userName, password = null;
        List<GrantedAuthority> authorities = null;
        Optional<UserEntity> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for the user : " + username);
        } else {
            userName = user.get().getEmail();
            password = user.get().getPassword();
            authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(user.get().getRole().toString()));
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMINISTRATOR"));
        }
        return new User(username, password, authorities);
    }




    private boolean isEmailUnique(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    public UserResponseDTO registerUser(SignUpDTO signUpDTO) {
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
//            savedUser.setRole(UserRole.ROLE_ADMINISTRATOR);
            savedUser = userRepository.save(savedUser);
            if (savedUser.getId() > 0) {
                newUser = userMapper.toResponseDto(savedUser);
            }

        } catch (Exception ex) {
            throw new CouldNotCreateUserException(savedUser.getEmail());
        }
        return newUser;
    }

    public UserResponseDTO setRole(SetRoleDTO setRoleDTO) {
        Optional<UserRole> userRole = userRoleService.getRoleByName(setRoleDTO.getRoleName());
        if (userRole.isEmpty()) {
            throw new UserRoleNotFoundException(setRoleDTO.getRoleName());
        }
        Optional<UserEntity> user = userRepository.findById(setRoleDTO.getUserID());
        if (user.isEmpty()) {
            throw new UserNotFoundException(setRoleDTO.getUserID());
        }

        Set<UserRole> userRoles = new HashSet<>(user.get().getRoles());
        userRoles.add(userRole.get());
        user.get().setRoles(userRoles);

        return userMapper.toResponseDto(userRepository.save(user.get()));
    }

    public Optional<UserEntity> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
