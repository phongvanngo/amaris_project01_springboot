package com.example.novapo_practice05.service.dto.User;

    import com.example.novapo_practice05.domain.UserEntity.UserRole;
    import java.time.Instant;
    import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;    private String email;
    private String accountNumber;
    private UserRole role;
    private Instant createdAt;

}
