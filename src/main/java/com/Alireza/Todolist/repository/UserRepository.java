package com.Alireza.Todolist.repository;

import com.Alireza.Todolist.entity.TaskEntity;
import com.Alireza.Todolist.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
