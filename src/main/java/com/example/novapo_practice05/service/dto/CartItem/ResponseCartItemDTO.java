package com.example.novapo_practice05.service.dto.CartItem;

import com.example.novapo_practice05.domain.Item;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ResponseCartItemDTO {
    private Long id;
    private Instant createdAt;
    private LocalDateTime deletedAt;
    private Long itemID;
    private Long userID;
    private int quantity;
}
