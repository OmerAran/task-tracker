package com.omeraran.task_tracker.application.auth;

import com.omeraran.task_tracker.application.auth.dto.UserLoginRequest;
import com.omeraran.task_tracker.application.auth.dto.UserRegisterRequest;
import com.omeraran.task_tracker.domain.enums.Role;
import com.omeraran.task_tracker.infrastructure.security.JwtTokenProvider;
import com.omeraran.task_tracker.infrastructure.user.UserEntity;
import com.omeraran.task_tracker.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/api/v1/auth/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest request) {
        if (repository.existsByUsername(request.username())) {
            throw new RuntimeException("Username is already taken");
        }
        var user = new UserEntity();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEmail(request.email());
        user.setRole(Role.USER);

        repository.save(user);

        return ResponseEntity.ok("User created");
    }

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
        var userEntity = repository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!passwordEncoder.matches(request.password(), userEntity.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        var token = jwtTokenProvider.generateToken(userEntity.getUsername(), userEntity.getRole());

        return ResponseEntity.ok(token);
    }
}

