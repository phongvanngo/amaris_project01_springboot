package com.example.novapo_practice05.service.mapper;

import com.example.novapo_practice05.domain.Catalog;
import com.example.novapo_practice05.service.dto.Catalog.CatalogDTO;
import com.example.novapo_practice05.service.dto.Catalog.ResponseCatalogDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogMapper {

    Catalog toEntity(CatalogDTO catalogDTO);

    ResponseCatalogDTO toResponseDTO(Catalog catalog);


}
