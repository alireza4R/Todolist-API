package com.Alireza.Todolist.controller;

import com.Alireza.Todolist.dto.AuthResponse;
import com.Alireza.Todolist.dto.LoginRequest;
import com.Alireza.Todolist.dto.RegisterRequest;
import com.Alireza.Todolist.exception.EmailAlreadyExistsException;
import com.Alireza.Todolist.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/register")
    public AuthResponse register (@RequestBody RegisterRequest registerRequest){
        return new AuthResponse(authService.register(registerRequest.name(), registerRequest.email(), registerRequest.password()));
    }

    @PostMapping(path = "/login")
    public AuthResponse login (@RequestBody LoginRequest loginRequest){
        return new AuthResponse(authService.login(loginRequest.email(), loginRequest.password()));
    }
}
