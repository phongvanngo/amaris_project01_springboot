package com.example.novapo_practice05.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="item")
public class Item extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="description")
    private String description;

    @Column(name="catalog_id")
    private Long catalogID;

}
