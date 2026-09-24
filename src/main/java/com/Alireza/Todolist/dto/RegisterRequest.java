package com.Alireza.Todolist.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(@NotBlank @Size(min = 3,max = 20,message = "name must have 3-20 character") String name,
                             @NotBlank @Email String email,
                      @NotBlank @Size(min = 4,max = 20,message = "password must have 4-20 character")        String password) {
}
