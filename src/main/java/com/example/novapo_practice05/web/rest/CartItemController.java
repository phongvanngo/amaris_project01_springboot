package com.example.novapo_practice05.web.rest;

import com.example.novapo_practice05.service.CartItemService;
import com.example.novapo_practice05.service.dto.CartItem.CartItemDTO;
import com.example.novapo_practice05.service.dto.CartItem.ResponseCartItemDTO;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseCartItemDTO> addToCart(@RequestBody @Valid CartItemDTO cartItem) {
        ResponseCartItemDTO responseCartItemDTO = cartItemService.addToCart(cartItem);
        return new ResponseEntity<ResponseCartItemDTO>(responseCartItemDTO, HttpStatus.OK);
    }

}
