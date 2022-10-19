package com.example.novapo_practice05.web.rest;

import com.example.novapo_practice05.service.CartItemService;
import com.example.novapo_practice05.service.dto.CartItem.CartItemDTO;
import com.example.novapo_practice05.service.dto.CartItem.ResponseCartItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {

    @Autowired
    CartItemService cartItemService;

    @PostMapping()
    public ResponseCartItemDTO addToCart(@RequestBody CartItemDTO cartItem) {
        return cartItemService.addToCart(cartItem);
    }
}
