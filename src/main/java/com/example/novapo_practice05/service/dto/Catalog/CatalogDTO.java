package com.example.novapo_practice05.service.dto.Catalog;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CatalogDTO {
    @NotNull
    private String name;

    private String description;

}
