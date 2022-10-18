package com.example.novapo_practice05.service.dto.Item;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemDTO {
    @NotNull
    private String name;

    private String description;

    @NotNull
    private Long catalogID;
}
