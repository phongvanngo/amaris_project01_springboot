package com.example.novapo_practice05.repository;

import com.example.novapo_practice05.domain.CartItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    @Query(value = "SELECT * FROM cart_item c where (c.user_id= :userID and c.item_id= :itemID)",nativeQuery = true)
    CartItem findCartItemByUserAndItem(Long userID,Long itemID);
}
