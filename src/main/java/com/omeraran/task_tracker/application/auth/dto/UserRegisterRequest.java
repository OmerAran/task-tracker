package com.omeraran.task_tracker.application.auth.dto;

public record UserRegisterRequest(String username, String password, String email) {
}
