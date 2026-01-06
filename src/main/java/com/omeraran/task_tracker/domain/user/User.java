package com.omeraran.task_tracker.domain.user;

import com.omeraran.task_tracker.domain.enums.Role;
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

    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }
}
