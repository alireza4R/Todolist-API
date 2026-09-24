package com.Alireza.Todolist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ListResponse(@JsonProperty("data") List<TaskResponse> listOfTasks,
                           Long page,
                           Long limitation,
                           Long total) {
}
