package com.example.user.exception.specific;

import com.example.user.exception.global.ConflictException;

import java.util.UUID;

public class ConcurrentBorrowException extends ConflictException {
    public ConcurrentBorrowException(UUID bookInstanceId) {
        super("Another user is already borrowing book instance "+bookInstanceId);
    }
}