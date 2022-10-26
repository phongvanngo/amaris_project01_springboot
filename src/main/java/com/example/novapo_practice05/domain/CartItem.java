package com.example.novapo_practice05.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="cart_item")
public class CartItem extends AbstractAuditingEntity{

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(name = "cart_item_id_seq", sequenceName = "cart_item_id_seq")
    private long id;

    @Column(name="item_id")
    private Long itemID;

    @Column(name="user_id")
    private Long userID;

    @Column(name = "quantity")
    private int quantity = 0;

}
