package com.example.novapo_practice05.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.novapo_practice05.domain.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    @Query(value = "SELECT * FROM cart_item c WHERE c.user_id= :userID and c.item_id= :itemID",nativeQuery = true)
    Optional<CartItem> findCartItemByUserAndItem(Long userID,Long itemID);

//    @Query(value = "SELECT * FROM cart_item c where (c.user_id= :userID and c.item_id= :itemID)",nativeQuery = true)
//    Optional<CartItem> findCartItemByUserAndItemOptional(Long userID,Long itemID);
}
