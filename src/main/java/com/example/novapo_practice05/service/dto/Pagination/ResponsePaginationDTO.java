package com.example.novapo_practice05.service.dto.Pagination;

import java.util.List;
import lombok.Data;

@Data
public class ResponsePaginationDTO<T> {
    private List<T> data;
    private int page;
    private int limit;
    private int totalPage;
}
