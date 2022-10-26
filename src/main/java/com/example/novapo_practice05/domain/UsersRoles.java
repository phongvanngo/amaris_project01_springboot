package com.example.novapo_practice05.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name="users_roles")
@IdClass(UsersRolesID.class)
@Data
public class UsersRoles implements Serializable {

    @Id
    @Column(name="role_id")
    private Long itemID;

    @Id
    @Column(name="user_id")
    private Long userID;

}
