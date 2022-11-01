package com.example.novapo_practice05.service;

import java.util.Optional;
import java.util.function.BiFunction;

import com.example.novapo_practice05.domain.CustomUserDetails;
import com.example.novapo_practice05.exception.ItemNotFoundException;
import com.example.novapo_practice05.service.dto.CartItem.AddToCartDTO;
import com.example.novapo_practice05.service.dto.CartItem.AddToCartMessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.novapo_practice05.domain.CartItem;
import com.example.novapo_practice05.domain.Item;
import com.example.novapo_practice05.domain.UserEntity;
import com.example.novapo_practice05.repository.CartItemRepository;
import com.example.novapo_practice05.repository.ItemRepository;
import com.example.novapo_practice05.repository.UserRepository;
import com.example.novapo_practice05.service.dto.CartItem.CartItemDTO;
import com.example.novapo_practice05.service.dto.CartItem.ResponseCartItemDTO;
import com.example.novapo_practice05.service.mapper.CartItemMapper;

@Service
public class CartItemService {

    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    private static final String TOPIC = "test_topic";

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    private final BiFunction<JpaRepository, Long, Boolean> checkRecordExist =
        (repository, id) -> repository.findById(id).isPresent();

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

        boolean isUserExist = this.checkRecordExist.apply(userRepository,userID);
        boolean isItemExist = checkRecordExist.apply(itemRepository,itemID);

        if (isUserExist && isItemExist) {
            CartItem newCartItem;
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
                newCartItem.setItemID(item.get().getId());
                newCartItem.setUserID(user.get().getId());
                newCartItem.setQuantity(requestQuantity);
            }
            newCartItem = cartItemRepository.save(newCartItem);
            System.out.println(newCartItem);
            return cartItemMapper.toResponseDTO(newCartItem);
        }

        return null;

    }

    /**
     *
     * @param cartItemDTO
     * @return
     */
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
        } else {
            newCartItem = new CartItem();
            newCartItem.setItemID(item.getId());
            newCartItem.setUserID(user.getId());
            newCartItem.setQuantity(requestQuantity);
        }
        newCartItem = cartItemRepository.save(newCartItem);
        System.out.println(newCartItem);
        return cartItemMapper.toResponseDTO(newCartItem);

    }

    public Optional<CartItem> findExistingCartItem(Long userID, Long itemID) {
        return cartItemRepository.findCartItemByUserAndItem(userID,itemID);
    }

    public Object addToCart(AddToCartDTO addToCartDTO) throws JsonProcessingException {
        //get user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String username = (String) authentication.getPrincipal();
        System.out.println(username);

        Optional<Item> itemOptioner = itemRepository.findById(addToCartDTO.getItemID());
        itemOptioner.orElseThrow(()-> new ItemNotFoundException(addToCartDTO.getItemID()));

        Item item = itemOptioner.get();

        AddToCartMessageDTO addToCartMessageDTO = new AddToCartMessageDTO();
        addToCartMessageDTO.setItemName(item.getName());
        addToCartMessageDTO.setUserName(username);
        addToCartMessageDTO.setItemID(item.getId());
        addToCartMessageDTO.setQuantity(addToCartDTO.getQuantity());

        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(addToCartMessageDTO);

        kafkaTemplate.send(TOPIC,message);

        return null;


        //throw message kafka
    }

}
