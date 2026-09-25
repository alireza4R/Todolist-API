package com.Alireza.Todolist.repository;

import com.Alireza.Todolist.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByUser_Id(Long userId);
}
