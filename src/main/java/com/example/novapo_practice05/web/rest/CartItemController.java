package com.example.novapo_practice05.web.rest;

import com.example.novapo_practice05.service.CartItemService;
import com.example.novapo_practice05.service.dto.CartItem.AddToCartDTO;
import com.example.novapo_practice05.service.dto.CartItem.CartItemDTO;
import com.example.novapo_practice05.service.dto.CartItem.ResponseCartItemDTO;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartItemController {

    @Autowired
    CartItemService cartItemService;

    @PostMapping("/cart")
    @RolesAllowed({"ROLE_CUSTOMER"})
    public ResponseEntity<ResponseCartItemDTO> addToCart(@RequestBody @Valid CartItemDTO cartItem) {
        ResponseCartItemDTO responseCartItemDTO = cartItemService.addToCart(cartItem);
        return new ResponseEntity<>(responseCartItemDTO, HttpStatus.OK);
    }

    @PostMapping("/add-to-cart")
    @RolesAllowed({"ROLE_CUSTOMER"})
    public Object addItemToCart(@RequestBody @Valid AddToCartDTO addToCartDTO) throws JsonProcessingException {
        return cartItemService.addToCart(addToCartDTO);
    }


}
