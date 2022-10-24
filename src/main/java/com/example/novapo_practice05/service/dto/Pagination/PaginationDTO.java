package com.example.novapo_practice05.service.dto.Pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDTO {
    private int page=0;
    private int limit=10;
}
