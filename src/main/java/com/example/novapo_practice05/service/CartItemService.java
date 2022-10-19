package com.example.novapo_practice05.service;

import com.example.novapo_practice05.domain.CartItem;
import com.example.novapo_practice05.domain.Item;
import com.example.novapo_practice05.domain.UserEntity;
import com.example.novapo_practice05.repository.CartItemRepository;
import com.example.novapo_practice05.repository.ItemRepository;
import com.example.novapo_practice05.repository.UserRepository;
import com.example.novapo_practice05.service.dto.CartItem.CartItemDTO;
import com.example.novapo_practice05.service.dto.CartItem.ResponseCartItemDTO;
import com.example.novapo_practice05.service.mapper.CartItemMapper;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.BiFunction;
import javax.swing.text.html.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    private static BiFunction<JpaRepository, Long, Boolean> checkRecodExist = (repository, id) -> {

        if (repository.findById(id).isPresent()) {
            return true;
        }
        return false;
    };


    public CartItemService(CartItemMapper cartItemMapper, CartItemRepository cartItemRepository,
        ItemRepository itemRepository, UserRepository userRepository) {
        this.cartItemMapper = cartItemMapper;
        this.cartItemRepository = cartItemRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public ResponseCartItemDTO addToCartBeforeRefractor(CartItemDTO cartItemDTO) {
        /***
         * Check user exists
         * Check item exists
         * auto increase quantity
         */
        Long userID = cartItemDTO.getUserID();
        Long itemID = cartItemDTO.getItemID();

        boolean isUserExist = checkRecodExist.apply(userRepository,userID);
        boolean isItemExist = checkRecodExist.apply(itemRepository,itemID);

        if (isUserExist && isItemExist) {
            CartItem newCartItem;
//            Optional<CartItem> existingCartItem = cartItemRepository.findCartItemByUserAndItem(userID,itemID);

//            Optional<CartItem> existingCartItem = Optional.ofNullable(cartItemRepository.findCartItemByUserAndItem(userID,itemID));
            Optional<CartItem> existingCartItem = cartItemRepository.findCartItemByUserAndItem(userID,itemID);

            int requestQuantity = cartItemDTO.getQuantity();
            if (existingCartItem.isPresent()) {
                int currentQuantity = existingCartItem.get().getQuantity();
                existingCartItem.get().setQuantity(currentQuantity+requestQuantity);
                newCartItem = existingCartItem.get();
            }
            else {
                newCartItem = new CartItem();

                Optional<Item> item = itemRepository.findById(itemID);
                Optional<UserEntity> user = userRepository.findById(userID);
                newCartItem.setItem(item.get());
                newCartItem.setUser(user.get());
                newCartItem.setQuantity(requestQuantity);
            }
            newCartItem = cartItemRepository.save(newCartItem);
            System.out.println(newCartItem);
            return cartItemMapper.toResponseDTO(newCartItem);
        }

        return null;

    }
    public ResponseCartItemDTO addToCart(CartItemDTO cartItemDTO) {
        /***
         * Check user exists
         * Check item exists
         * auto increase quantity
         */

        Long userID = cartItemDTO.getUserID();
        Long itemID = cartItemDTO.getItemID();

        Item item = itemService.getbByID(itemID);
        UserEntity user = userService.getByID(userID);

        CartItem newCartItem;

        Optional<CartItem> existingCartItem = findExistingCartItem(userID,itemID);

        int requestQuantity = cartItemDTO.getQuantity();

        if (existingCartItem.isPresent()) {
            int currentQuantity = existingCartItem.get().getQuantity();
            existingCartItem.get().setQuantity(currentQuantity+requestQuantity);
            newCartItem = existingCartItem.get();
        }
        else {
            newCartItem = new CartItem();
            newCartItem.setItem(item);
            newCartItem.setUser(user);
            newCartItem.setQuantity(requestQuantity);
        }
        newCartItem = cartItemRepository.save(newCartItem);
        System.out.println(newCartItem);
        return cartItemMapper.toResponseDTO(newCartItem);

    }

    public Optional<CartItem> findExistingCartItem(Long userID, Long itemID) {
        return cartItemRepository.findCartItemByUserAndItem(userID,itemID);
    }

}
