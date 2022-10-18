package com.example.novapo_practice05.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
@Data
public abstract class AbstractAuditingEntity {

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @Column(name ="deleted_at")
    private LocalDateTime deletedAt;

    @LastModifiedDate
    @Column(name ="updated_at")
    private LocalDateTime updatedAt;
}
