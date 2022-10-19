package com.example.novapo_practice05.service.dto.CartItem;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartItemDTO {

    @NotNull
    private Long itemID;

    @NotNull
    private Long userID;

    @NotNull
    private int quantity;

}
