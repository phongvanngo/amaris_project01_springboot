package com.example.novapo_practice05.repository.CustomRepository;

public interface CartItemCustomRepository {
    boolean checkCartItemExist(Long userID,Long itemID);
}
