package com.example.novapo_practice05.web.rest;

import com.example.novapo_practice05.service.CatalogService;
import com.example.novapo_practice05.service.dto.Catalog.CatalogDTO;
import com.example.novapo_practice05.service.dto.Catalog.ResponseCatalogDTO;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @GetMapping()
    public List<ResponseCatalogDTO> getAllCatalog() {
        return catalogService.getCatalog();
    }

    @PostMapping()
    @RolesAllowed({"ROLE_ADMIN","ROLE_EDITOR"})
    public ResponseCatalogDTO createCatalog(@RequestBody CatalogDTO catalogDTO) {
        return catalogService.createCatalog(catalogDTO);
    }

    @PutMapping("/{catalogID}")
    @RolesAllowed({"ROLE_ADMIN","ROLE_EDITOR"})
    public ResponseCatalogDTO updateCatalog(@RequestBody CatalogDTO catalogDTO, @PathVariable long catalogID) {
        return catalogService.updateCatalog(catalogDTO, catalogID);
    }

    @DeleteMapping("/{catalogID}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseCatalogDTO deleteCatalog(@PathVariable long catalogID) {
        return catalogService.deleteCatalog(catalogID);
    }

}
