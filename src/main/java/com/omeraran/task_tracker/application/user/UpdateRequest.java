package com.omeraran.task_tracker.application.user;

import com.omeraran.task_tracker.domain.user.User;

public record UpdateRequest(String email, String fullName) {
    public User toModel() {
        return new User(email, fullName);
    }
}
