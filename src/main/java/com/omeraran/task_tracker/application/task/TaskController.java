package com.omeraran.task_tracker.application.task;

import com.omeraran.task_tracker.application.task.dto.TaskRequest;
import com.omeraran.task_tracker.application.task.dto.TaskResponse;
import com.omeraran.task_tracker.application.task.dto.TaskUpdateRequest;
import com.omeraran.task_tracker.domain.task.Task;
import com.omeraran.task_tracker.domain.task.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService service;

    @GetMapping("/api/v1/users/{userId}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponse> retrieveAll(@PathVariable Long userId){
        log.info("[START] TaskController - retrieveAll - user id: {}", userId);
        var tasks = service.retrieveAll(userId);

        return tasks.stream()
                .map(task -> new TaskResponse(
                        task.getId(),
                        task.getUserId(),
                        task.getName(),
                        task.getPoint(),
                        task.getStatus())).collect(Collectors.toList());
    }

    @GetMapping("/api/v1/users/{userId}/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse retrieve(@PathVariable Long userId, @PathVariable Long id){
        log.info("[START] TaskController - retrieve - user id: {} and task id: {}", userId, id);
        Task retrieved = service.retrieve(userId, id);
        return new TaskResponse(
                retrieved.getId(),
                retrieved.getUserId(),
                retrieved.getName(),
                retrieved.getPoint(),
                retrieved.getStatus()
        );
    }

    @PostMapping("/api/v1/users/{userId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public TaskResponse create(@PathVariable Long userId, @Valid @RequestBody TaskRequest request){
        log.info("[START] TaskController - create - user id : {}", userId);

        var task = new Task(
                request.id(),
                userId,
                request.name(),
                request.point(),
                request.status()
        );

        var retrieved = service.create(userId, task);

        return new TaskResponse(
                retrieved.getId(),
                retrieved.getUserId(),
                retrieved.getName(),
                retrieved.getPoint(),
                retrieved.getStatus()
        );
    }

    @PutMapping("/api/v1/users/{userId}/tasks/{id}/status-change")
    @ResponseStatus(HttpStatus.OK)
    public void updateTaskStatus(@PathVariable Long userId, @PathVariable Long id, @RequestBody TaskUpdateRequest request){
        log.info("[START] TaskController - updateTaskStatus - user id: {}", userId);
        var task = new Task();
        task.setStatus(request.status());
        service.updateTaskStatus(userId, id, task);
    }

    @PutMapping("/api/v1/users/{userId}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse update(@PathVariable Long userId, @Valid @RequestBody TaskRequest request){
        log.info("[START] TaskController - update - user id: {} and task id: {}", userId, request.id());

        var task = new Task(
                request.id(),
                userId,
                request.name(),
                request.point(),
                request.status()
        );

        var updated = service.update(userId, task);

        return new TaskResponse(
                updated.getId(),
                updated.getUserId(),
                updated.getName(),
                updated.getPoint(),
                updated.getStatus()
        );
    }

    @DeleteMapping("/api/v1/users/{userId}/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long userId, @PathVariable Long id){
        log.info("[START] TaskController - delete - user id: {} and task id: {}", userId, id);

        service.delete(userId, id);

        log.info("[END] TaskController - delete - user id : {} and task id: {}", userId, id);
    }
}
