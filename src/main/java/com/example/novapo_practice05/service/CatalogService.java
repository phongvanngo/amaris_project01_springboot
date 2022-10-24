package com.example.novapo_practice05.service;

import com.example.novapo_practice05.domain.Catalog;
import com.example.novapo_practice05.repository.CatalogRepository;
import com.example.novapo_practice05.service.dto.Catalog.CatalogDTO;
import com.example.novapo_practice05.service.dto.Catalog.CatalogParamsDTO;
import com.example.novapo_practice05.service.dto.Catalog.ResponseCatalogDTO;
import com.example.novapo_practice05.service.dto.Pagination.ResponsePaginationDTO;
import com.example.novapo_practice05.service.mapper.CatalogMapper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

    private final CatalogRepository catalogRepository;
    private final CatalogMapper catalogMapper;

    public CatalogService(CatalogRepository catalogRepository, CatalogMapper catalogMapper) {
        this.catalogRepository = catalogRepository;
        this.catalogMapper = catalogMapper;
    }

    private List<ResponseCatalogDTO> toResponseDTOs(List<Catalog> catalogs) {
        List<ResponseCatalogDTO> res = new ArrayList<>();
        for (Catalog catalog :
            catalogs) {
            res.add(catalogMapper.toResponseDTO(catalog));
        }

        return res;
    }

    public ResponseCatalogDTO createCatalog(CatalogDTO catalog) {
        Catalog newCatalog = catalogRepository.save(catalogMapper.toEntity(catalog));
        return catalogMapper.toResponseDTO(newCatalog);
    }

    public ResponseCatalogDTO updateCatalog(CatalogDTO catalog, long catalogID) {

        Catalog catalogToUpdate = catalogMapper.toEntity(catalog);
        catalogToUpdate.setId(catalogID);
        catalogToUpdate = catalogRepository.save(catalogToUpdate);
        return catalogMapper.toResponseDTO(catalogToUpdate);
    }

    public ResponseCatalogDTO deleteCatalog(long catalogID) {
        Optional<Catalog> catalogToDelete = catalogRepository.findById(catalogID);
        if (catalogToDelete.isPresent()) {
            catalogToDelete.get().setDeletedAt(LocalDateTime.now());
            Catalog response = catalogRepository.save(catalogToDelete.get());
            return catalogMapper.toResponseDTO(response);
        } else {
            return null;
        }
    }

    public List<ResponseCatalogDTO> getCatalog() {
        List<ResponseCatalogDTO> results = new ArrayList<ResponseCatalogDTO>();
        List<Catalog> catalogs = catalogRepository.findAll();
        for (Catalog catalog : catalogs
        ) {
            results.add(catalogMapper.toResponseDTO(catalog));
        }
        return results;
    }

    public ResponsePaginationDTO<ResponseCatalogDTO> getCatalogPagaination(CatalogParamsDTO catalogParamsDTO) {
        int page = catalogParamsDTO.getPage();
        int limit = catalogParamsDTO.getLimit();

        Page<Catalog> catalogPage = catalogRepository.findAll(PageRequest.of(page, limit));
        List<Catalog> catalogs = catalogPage.get().collect(Collectors.toList());

        ResponsePaginationDTO<ResponseCatalogDTO> response = new ResponsePaginationDTO<>(

        );

        return response.setPage(page)
            .setLimt(limit)
            .setTotalPage(catalogPage.getTotalPages())
            .setData(toResponseDTOs(catalogs));
    }

}
