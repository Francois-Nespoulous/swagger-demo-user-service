package com.example.user.exception.specific;

import com.example.user.exception.global.ConflictException;

public class UserAlreadyExistsException extends ConflictException {
    public UserAlreadyExistsException(String username) {
        super("User "+username+" already exists");
    }
}