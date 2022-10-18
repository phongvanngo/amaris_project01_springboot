package com.example.novapo_practice05.service.mapper;
import com.example.novapo_practice05.domain.AbstractAuditingEntity;
import com.example.novapo_practice05.domain.UserEntity;
import com.example.novapo_practice05.service.dto.User.SignUpDTO;
import com.example.novapo_practice05.service.dto.User.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source="createdAt",target="createdAt")
    UserResponseDTO toResponseDto(UserEntity userEntity);

    UserEntity toEntity(SignUpDTO signUpDTO);
}
