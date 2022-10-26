package com.example.novapo_practice05.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersRolesID implements Serializable {

    private Long userID;
    private Long itemID;
}
