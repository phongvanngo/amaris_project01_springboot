package com.example.novapo_practice05.service.mapper;

import com.example.novapo_practice05.domain.CartItem;
import com.example.novapo_practice05.service.dto.CartItem.CartItemDTO;
import com.example.novapo_practice05.service.dto.CartItem.ResponseCartItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItem toEntity(CartItemDTO cartItemDTO);

    @Mapping(source="item.id",target = "itemID")
    @Mapping(source="user.id",target = "userID")
    ResponseCartItemDTO toResponseDTO(CartItem cartItem);
}
