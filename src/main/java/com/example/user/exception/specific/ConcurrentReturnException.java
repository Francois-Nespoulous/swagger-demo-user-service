package com.example.user.exception.specific;

import com.example.user.exception.global.ConflictException;

import java.util.UUID;

public class ConcurrentReturnException extends ConflictException {
    public ConcurrentReturnException(UUID bookInstanceId) {
        super("Another user is already returning book instance "+bookInstanceId);
    }
}