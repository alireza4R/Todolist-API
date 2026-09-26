package com.Alireza.Todolist.service;

import com.Alireza.Todolist.dto.ListResponse;
import com.Alireza.Todolist.dto.TaskResponse;
import com.Alireza.Todolist.entity.TaskEntity;
import com.Alireza.Todolist.entity.UserEntity;
import com.Alireza.Todolist.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponse createTask (String title, String description, UserEntity user){
        TaskEntity task = new TaskEntity(null, title, description, user);
        task = taskRepository.save(task);
        return new TaskResponse(task.getId(), task.getTitle(), task.getDescription());
    }

    @Transactional
    public TaskResponse updateTask (Long id, String title, String description, UserEntity user) throws Exception {
        TaskEntity task = getTask(id, user);
        task.setDescription(description);
        task.setTitle(title);
        TaskEntity savedTask = taskRepository.save(task);
        return new TaskResponse(savedTask.getId(), savedTask.getTitle(), savedTask.getDescription());
    }
    @Transactional
    public void deleteTask (Long id, UserEntity user) throws Exception {
        TaskEntity task = getTask(id, user);
        taskRepository.delete(task);
    }


    public ListResponse getAllTasks(int page, int limit, UserEntity user){
        Pageable pageable = PageRequest.of(page -1,limit, Sort.by("id").ascending());

        Page<TaskEntity> taskPage = taskRepository.findByUser_Id(user.getId(),pageable);
        List<TaskEntity> taskEntities = taskPage.getContent();
        List<TaskResponse> taskResponses = new ArrayList<>();

        for(TaskEntity taskEntity: taskEntities){
            taskResponses.add(new TaskResponse(taskEntity.getId(), taskEntity.getTitle(), taskEntity.getDescription()));
        }
        return new ListResponse(taskResponses, page, limit, (int)taskPage.getTotalElements());
    }

    private TaskEntity getTask(Long id, UserEntity user) throws Exception {
        TaskEntity task = taskRepository.findById(id).orElseThrow(()->new Exception("Task not found"));
        if(!task.getUser().getId().equals(user.getId())){
            throw new AccessDeniedException("You cannot access this task");
        }
        return task;
    }



}
