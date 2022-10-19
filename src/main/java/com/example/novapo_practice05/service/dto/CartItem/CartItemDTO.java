package com.example.novapo_practice05.service.dto.CartItem;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CartItemDTO {

    @NotNull(message = "itemID must not be blank")
    private Long itemID;

    @NotNull(message = "userID must not be blank")
    private Long userID;

    @Min(1)
    private int quantity;

}
