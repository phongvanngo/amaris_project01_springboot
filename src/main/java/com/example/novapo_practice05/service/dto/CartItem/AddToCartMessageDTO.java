package com.example.novapo_practice05.service.dto.CartItem;

import lombok.Data;

@Data
public class AddToCartMessageDTO {
    String username;
    Long itemID;
    String itemName;
    Integer quantity;
}