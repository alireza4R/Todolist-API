package com.Alireza.Todolist.repository;

import com.Alireza.Todolist.entity.TaskEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Page<TaskEntity> findByUser_Id(Long userId, Pageable pageable);
}
