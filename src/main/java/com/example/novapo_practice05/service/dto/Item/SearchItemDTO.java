package com.example.novapo_practice05.service.dto.Item;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class SearchItemDTO {

    @Min(0)
    @Max(100)
    private int limit = 10;

    private int page = 100;
    private String name;
    private int id;
    private String description;
    private Long catalogID;
}
