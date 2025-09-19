package com.example.user.exception.specific;

import com.example.user.exception.global.NotFoundException;

import java.util.UUID;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(UUID userId) {
        super("User "+userId+" not found");
    }
}