package com.example.novapo_practice05.service.third_party;

import com.example.novapo_practice05.service.dto.CartItem.ItemInCartDTO;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;
import java.util.List;

@FeignClient(name = "cartservice", url = "localhost:3000/api")
public interface CartItemExternalService {

    @GetMapping(value = "/cart/ping",consumes= MediaType.APPLICATION_JSON_VALUE)
    public String pingToCartService();

    @GetMapping(value = "/cart/items/{username}",consumes= MediaType.APPLICATION_JSON_VALUE)
    public List<ItemInCartDTO> getAllItemsInCart(@PathVariable(value = "username") String username);
}
