package com.example.novapo_practice05.service.dto.CartItem;

import lombok.Data;

@Data
public class AddToCartDTO {
    private Long itemID;
    private Integer quantity;
}