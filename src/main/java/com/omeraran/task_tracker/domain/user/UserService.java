package com.omeraran.task_tracker.domain.user;

import com.omeraran.task_tracker.infrastructure.user.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User retrieve(Long id){
        return repository.retrieve(id).orElseThrow(RuntimeException::new).toModel();
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
        entity.setEmail(request.getEmail());
    }

}
