package com.example.novapo_practice05.service.dto.Catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogParamsDTO  {
    private int page=0;
    private int limit=10;
}
