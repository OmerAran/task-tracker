package com.omeraran.task_tracker.application.user;

import com.omeraran.task_tracker.domain.user.User;
import jakarta.validation.constraints.Email;

public record UpdateRequest(
        @Email(message = "Email format is not inappropriate.") String email) {

    public User toModel() {
        var user = new User();
        user.setEmail(email);
       return user;
    }
}
