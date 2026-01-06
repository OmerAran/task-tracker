package com.omeraran.task_tracker.application.user;


import com.omeraran.task_tracker.domain.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;

    @GetMapping("/api/v1/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ADMIN'))")
    public UserResponse retrieve(@PathVariable Long id) {
        log.info("[START] UserController - retrieve - user id: {}", id);
        var user = service.retrieve(id);
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }


    @PutMapping("/api/v1/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ADMIN')")
    public UserResponse update(@PathVariable Long id,@Valid @RequestBody UpdateRequest request) {
        log.info("[START] UserController - update - user id: {}", id);
        var updated = service.update(id, request.toModel());
        return new UserResponse(updated.getId(), updated.getUsername(), updated.getEmail());

    }

    @DeleteMapping("/api/v1/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ADMIN')")
    public void delete(@PathVariable Long id) {
        log.info("[START] UserController - delete - user id: {}", id);
        service.delete(id);
        log.info("[END] UserController - delete - user id : {}", id);
    }
}
