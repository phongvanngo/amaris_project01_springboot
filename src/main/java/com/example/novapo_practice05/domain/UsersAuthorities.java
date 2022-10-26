package com.example.novapo_practice05.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users_authorities")
public class UsersAuthorities implements Serializable {

    @Id
    @Column(name="item_id")
    private Long itemID;

    @Id
    @Column(name="user_id")
    private Long userID;
}
