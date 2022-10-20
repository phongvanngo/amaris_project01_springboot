package com.example.novapo_practice05.repository;

import com.example.novapo_practice05.domain.CartItem;
import com.example.novapo_practice05.repository.CustomRepository.CartItemCustomRepository;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long>, CartItemCustomRepository {

    @Query(value = "SELECT * FROM cart_item c WHERE c.user_id= :userID and c.item_id= :itemID",nativeQuery = true)
    Optional<CartItem> findCartItemByUserAndItem(Long userID,Long itemID);

//    @Query(value = "SELECT * FROM cart_item c where (c.user_id= :userID and c.item_id= :itemID)",nativeQuery = true)
//    Optional<CartItem> findCartItemByUserAndItemOptional(Long userID,Long itemID);
}
