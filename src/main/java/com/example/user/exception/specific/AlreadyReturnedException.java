package com.example.user.exception.specific;

import com.example.user.exception.global.ConflictException;

import java.util.UUID;

public class AlreadyReturnedException extends ConflictException {
    public AlreadyReturnedException(UUID bookInstanceId) {
        super("Book instance "+bookInstanceId+" already returned");
    }
}