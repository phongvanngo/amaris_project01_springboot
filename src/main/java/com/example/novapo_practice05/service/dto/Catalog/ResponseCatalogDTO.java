package com.example.novapo_practice05.service.dto.Catalog;

import java.time.Instant;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ResponseCatalogDTO {

    private Long id;
    private String name;
    private String description;
    private Instant createdAt;
    private LocalDateTime deletedAt;

    private String updatedBy;
    private String createdBy;
    private String deletedBy;
}
