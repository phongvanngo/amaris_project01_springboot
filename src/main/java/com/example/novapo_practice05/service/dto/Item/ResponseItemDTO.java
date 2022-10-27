package com.example.novapo_practice05.service.dto.Item;

import com.example.novapo_practice05.domain.Catalog;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ResponseItemDTO {
    private Long id;
    private Long catalogID;
    private String name;
    private String description;
    private Instant createdAt;
    private LocalDateTime deletedAt;
    private String catalogName;

    public ResponseItemDTO(Long id, Long catalogID, String name, String catalogName) {
        this.id = id;
        this.catalogID = catalogID;
        this.name = name;
        this.catalogName = catalogName;
    }

    public ResponseItemDTO() {
    }
}
