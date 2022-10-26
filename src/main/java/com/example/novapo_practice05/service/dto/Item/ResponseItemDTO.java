package com.example.novapo_practice05.service.dto.Item;

import com.example.novapo_practice05.domain.Catalog;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ResponseItemDTO {
    private Long id;
    private Long catalogID;
    private String name;
    private String description;
    private Instant createdAt;
    private LocalDateTime deletedAt;
}
