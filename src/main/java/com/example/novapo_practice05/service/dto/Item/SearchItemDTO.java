package com.example.novapo_practice05.service.dto.Item;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class SearchItemDTO {
    @Nullable
    private String name;
    @Nullable
    private String description;
    @Nullable
    private Integer limit = 10;
    @Nullable
    private Integer page = 0;
    @Nullable
    private Integer id=null;
    @Nullable
    private Long catalogID=null;
}
