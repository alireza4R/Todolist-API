package com.Alireza.Todolist.controller;

import com.Alireza.Todolist.dto.PostRequest;
import com.Alireza.Todolist.dto.TaskResponse;
import com.Alireza.Todolist.service.TaskService;
import org.springframework.security.oauth2.jwt.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping(path = "/todos")
    public TaskResponse createTask(@RequestBody PostRequest postRequest,
                                   @AuthenticationPrincipal Jwt jwt) throws Exception {
        Long id = Long.valueOf(jwt.getSubject());
        return taskService.createTask(postRequest.title(), postRequest.description(), id);
    }


}
