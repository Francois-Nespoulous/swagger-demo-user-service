package com.example.user.exception.specific;

import com.example.user.exception.global.ForbiddenException;

public class InvalidCredentialsException extends ForbiddenException {
    public InvalidCredentialsException(String username) {
        super("Invalid credentials for user "+username);
    }
}