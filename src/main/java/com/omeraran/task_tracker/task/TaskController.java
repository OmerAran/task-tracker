package com.omeraran.task_tracker.task;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService service;

    @GetMapping("/api/v1/users/{userId}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> retrieveAll(@PathVariable Long userId){
        log.info("[START] TaskController - retrieveAll - user id: {}", userId);
        return service.retrieveAll(userId);
    }

    @GetMapping("/api/v1/users/{userId}/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task retrieve(@PathVariable Long userId, @PathVariable Long id){
        log.info("[START] TaskController - retrieve - user id: {} and task id: {}", userId, id);
        return service.retrieve(userId, id);

    }

    @PostMapping("/api/v1/users/{userId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public Task create(@PathVariable Long userId, @RequestBody Task request){
        log.info("[START] TaskController - create - user id : {}", userId);
        return service.create(userId, request);
    }

    @PutMapping("/api/v1/users/{userId}/tasks/{id}/status-change")
    @ResponseStatus(HttpStatus.OK)
    public void updateTaskStatus(@PathVariable Long userId, @PathVariable Long id, @RequestBody Task request){
        log.info("[START] TaskController - updateTaskStatus - user id: {}", userId);
        service.updateTaskStatus(userId, id, request);
    }

    @PutMapping("/api/v1/users/{userId}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public Task update(@PathVariable Long userId, @RequestBody Task request){
        log.info("[START] TaskController - update - user id: {} and task id: {}", userId, request.getId());
        return service.update(userId, request);

    }

    @DeleteMapping("/api/v1/users/{userId}/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long userId, @PathVariable Long id){
        log.info("[START] TaskController - delete - user id: {} and task id: {}", userId, id);

        service.delete(userId, id);

        log.info("[END] TaskController - delete - user id : {} and task id: {}", userId, id);
    }
}
