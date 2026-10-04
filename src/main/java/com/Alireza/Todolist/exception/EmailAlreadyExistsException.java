package com.Alireza.Todolist.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String userExist) {
        super(userExist);
    }
}
