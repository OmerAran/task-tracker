package com.omeraran.task_tracker;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository repository;

    public List<Task> retrieveAll(Long userId) {

        var taskEntities = repository.retrieveAll(userId);

        return taskEntities.stream()
                .map(TaskEntity::toModel)
                .toList();
    }

    public Task retrieve(Long userId, Long id) {
        return repository.retrieve(userId, id)
                .orElseThrow()
                .toModel();
    }

    public Task create(Long userId, Task request) {
        var entity = new TaskEntity();
        populate(request, userId, entity);
        return repository.save(entity).toModel();
    }



    public void updateTaskStatus(Long userId, Long id, Task request) {
        var oldEntity = repository.retrieve(userId, id)
                .orElseThrow();

        oldEntity.setStatus(request.getStatus());

        repository.save(oldEntity);

    }

    public Task update(Long userId, Task request) {
        var oldEntity = repository.retrieve(userId, request.getId())
                .orElseThrow();

        populate(request, oldEntity);
        return repository.save(oldEntity).toModel();
    }

    public void delete(Long userId, Long id) {
        var taskEntity = repository.retrieve(userId, id);
        taskEntity.ifPresent(entity -> repository.delete(entity));
    }

    private void populate(Task request, Long userId, TaskEntity entity) {
        entity.setUserId(userId);
        entity.setName(request.getName());
        entity.setPoint(request.getPoint());
        entity.setStatus(request.getStatus());
    }

    private void populate(Task request, TaskEntity entity) {
        entity.setName(request.getName());
        entity.setPoint(request.getPoint());
        entity.setStatus(request.getStatus());
    }
}
