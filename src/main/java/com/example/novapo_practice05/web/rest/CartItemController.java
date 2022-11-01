package com.example.novapo_practice05.web.rest;

import com.example.novapo_practice05.service.CartItemService;
import com.example.novapo_practice05.service.dto.CartItem.AddToCartDTO;
import com.example.novapo_practice05.service.dto.CartItem.CartItemDTO;
import com.example.novapo_practice05.service.dto.CartItem.ItemInCartDTO;
import com.example.novapo_practice05.service.dto.CartItem.ResponseCartItemDTO;
import com.example.novapo_practice05.service.third_party.CartItemExternalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CartItemController {

    @Autowired
    CartItemService cartItemService;

    @Autowired
    CartItemExternalService cartItemExternalService;

    @PostMapping("/cart")
    @RolesAllowed({"ROLE_CUSTOMER"})
    public ResponseEntity<ResponseCartItemDTO> addToCart(@RequestBody @Valid CartItemDTO cartItem) {
        ResponseCartItemDTO responseCartItemDTO = cartItemService.addToCart(cartItem);
        return new ResponseEntity<>(responseCartItemDTO, HttpStatus.OK);
    }

    @PostMapping("/add-to-cart")
//    @RolesAllowed({"ROLE_CUSTOMER"})
    public Object addItemToCart(@RequestBody @Valid AddToCartDTO addToCartDTO) throws JsonProcessingException {
        return cartItemService.addToCart(addToCartDTO);
    }

    @GetMapping("/ping-service/cart-service")
    public String addItemToCart() {
        String res = cartItemExternalService.pingToCartService();
        System.out.println(res);
        return res;
    }

    @GetMapping("/items")
    public Object getItemsInCart() throws URISyntaxException {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        List<ItemInCartDTO> res =  cartItemExternalService.getAllItemsInCart(username);
        return res;
    }


}
