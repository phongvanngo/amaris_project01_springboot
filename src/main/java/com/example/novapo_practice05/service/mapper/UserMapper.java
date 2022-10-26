package com.example.novapo_practice05.service.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.novapo_practice05.domain.UserEntity;
import com.example.novapo_practice05.service.dto.User.SignUpDTO;
import com.example.novapo_practice05.service.dto.User.UserResponseDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source="createdAt",target="createdAt")
    UserResponseDTO toResponseDto(UserEntity userEntity);

    UserEntity toEntity(SignUpDTO signUpDTO);
}
