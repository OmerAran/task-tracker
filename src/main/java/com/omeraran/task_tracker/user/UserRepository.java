package com.omeraran.task_tracker.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    @Query(value = """
            select * from users where id = :id
            """, nativeQuery = true)
    Optional<UserEntity> retrieve(Long id);
}
