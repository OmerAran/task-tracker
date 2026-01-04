package com.omeraran.task_tracker.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User retrieve(Long id){
        return repository.retrieve(id).orElseThrow(RuntimeException::new).toModel();
    }

    public User create(User request) {
        var entity = new UserEntity();
        populate(request, entity);
        return repository.save(entity).toModel();
    }

    public User update(Long id, User request) {
        var oldEntity = repository.retrieve(id)
                .orElseThrow();

        populate(request, oldEntity);
        return repository.save(oldEntity).toModel();
    }

    public void delete(Long id) {
        var userEntity = repository.retrieve(id);
        userEntity.ifPresent(repository::delete);
    }

    private void populate(User request, UserEntity entity) {
        entity.setId(request.getId());
        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());
    }

}
