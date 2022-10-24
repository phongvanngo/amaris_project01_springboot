package com.example.novapo_practice05.service.dto.Item;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;
import org.springframework.lang.Nullable;

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
