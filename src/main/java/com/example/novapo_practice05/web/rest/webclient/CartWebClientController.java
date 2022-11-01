package com.example.novapo_practice05.web.rest.webclient;

import com.example.novapo_practice05.service.dto.CartItem.ItemInCartDTO;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.security.RolesAllowed;

@RestController
public class CartWebClientController {
    private static final int DEFAULT_PORT = 3000;

    @Setter
    private int serverPort = DEFAULT_PORT;


    @GetMapping("/items-in-cart")
    public Flux<ItemInCartDTO> getTweetsNonBlocking() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Flux<ItemInCartDTO> tweetFlux = WebClient.create()
                .get()
                .uri(getSlowServiceUri("/cart/items/"+username))
                .retrieve()
                .bodyToFlux(ItemInCartDTO.class);
        return tweetFlux;
    }
    private String getSlowServiceUri(String url) {
        return "http://localhost:" + serverPort + "/api"+url;
    }
}
