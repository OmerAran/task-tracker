package com.omeraran.task_tracker.domain.user;

import com.omeraran.task_tracker.domain.enums.Role;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;

    public User(@Email(message = "Email format is not inappropriate.") String email) {
    }
}
