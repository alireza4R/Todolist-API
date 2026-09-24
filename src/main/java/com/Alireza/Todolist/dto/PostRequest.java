package com.Alireza.Todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(@NotBlank @Size(max = 20,message = "title can have at most 20 characters") String title,
                       @NotBlank @Size(max = 254, message = "description can have at most 254 characters")   String description) {
}
