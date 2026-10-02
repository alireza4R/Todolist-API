package com.Alireza.Todolist.controller;

import com.Alireza.Todolist.dto.ListResponse;
import com.Alireza.Todolist.dto.PostRequest;
import com.Alireza.Todolist.dto.PutRequest;
import com.Alireza.Todolist.dto.TaskResponse;
import com.Alireza.Todolist.service.TaskService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping(path = "/todos")
    public TaskResponse createTask(@RequestBody PostRequest postRequest,
                                   @AuthenticationPrincipal Jwt jwt) {
        Long userId = Long.valueOf(jwt.getSubject());
        return taskService.createTask(postRequest.title(), postRequest.description(), userId);
    }

    @PutMapping("/todos/{id}")
    public TaskResponse putTask(@RequestBody PutRequest putRequest,
                                @AuthenticationPrincipal Jwt jwt,
                                @PathVariable("id") Long taskId) {
        Long userId = Long.valueOf(jwt.getSubject());
        return taskService.updateTask(taskId, putRequest.title(), putRequest.description(), userId);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long taskId,
                                             @AuthenticationPrincipal Jwt jwt) {
        Long userId = Long.valueOf(jwt.getSubject());
        taskService.deleteTask(taskId, userId );
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/todos")
    public ListResponse getAllTasks (@RequestParam @Min(1) int page,
                                     @RequestParam @Min(1) @Max(1000) int limit,
                                    @AuthenticationPrincipal Jwt jwt){
        Long userId = Long.valueOf(jwt.getSubject());
        return taskService.getAllTasks(page, limit, userId);
    }





}
