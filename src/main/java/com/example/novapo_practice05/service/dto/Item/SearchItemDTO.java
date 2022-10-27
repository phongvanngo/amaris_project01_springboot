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
    private int limit =10;
    @Nullable
    private int page = 0;
    @Nullable
    private Long id=null;
    @Nullable
    private Long catalogID=null;
}
