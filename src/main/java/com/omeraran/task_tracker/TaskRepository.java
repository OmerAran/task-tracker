package com.omeraran.task_tracker;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Long> {


    @Query(value = """
            select * from task where user_id = :userId
            """, nativeQuery = true)
    List<TaskEntity> retrieveAll(Long userId);


    @Query(value = """
           select * from task where user_id = :userId and id = :id
            """, nativeQuery = true)
    Optional<TaskEntity> retrieve(Long userId, Long id);
}
