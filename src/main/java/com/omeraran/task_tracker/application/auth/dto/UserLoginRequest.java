package com.omeraran.task_tracker.application.auth.dto;


import jakarta.validation.constraints.Size;

public record UserLoginRequest(
        @Size(min = 3,max = 20, message = "Size should be at least 3 and max 20 characters.") String username,
        @Size(min = 6,max = 20, message = "password should be at least 6 and max 20 characters.") String password) {
}
