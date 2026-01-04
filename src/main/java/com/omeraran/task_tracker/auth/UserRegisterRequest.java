package com.omeraran.task_tracker.auth;

public record UserRegisterRequest(String username, String password, String email) {
}
