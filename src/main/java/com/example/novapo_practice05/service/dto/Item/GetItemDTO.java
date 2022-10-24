package com.example.novapo_practice05.service.dto.Item;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetItemDTO {

    private int limit;
    private int page;
}
