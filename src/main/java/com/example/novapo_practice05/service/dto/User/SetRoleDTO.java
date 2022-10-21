package com.example.novapo_practice05.service.dto.User;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SetRoleDTO {
    @NotNull(message = "user_id cannot be null")
    private Long userID;
    @NotNull(message = "role_name cannot be null")
    private String roleName;
}
