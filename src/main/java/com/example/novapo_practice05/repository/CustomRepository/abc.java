package com.example.novapo_practice05.repository.CustomRepository;

public class abc implements CartItemCustomRepository{

    @Override
    public boolean checkCartItemExist(Long userID, Long itemID) {
        return false;
    }
}
