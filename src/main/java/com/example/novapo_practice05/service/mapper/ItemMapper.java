package com.example.novapo_practice05.service.mapper;

import com.example.novapo_practice05.domain.Item;
import com.example.novapo_practice05.service.dto.Item.ItemDTO;
import com.example.novapo_practice05.service.dto.Item.ResponseItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toEntity(ItemDTO itemDTO);

    @Mapping(source = "catalog.id",target ="catalogID" )
    ResponseItemDTO toResponseDTO(Item item);
}
