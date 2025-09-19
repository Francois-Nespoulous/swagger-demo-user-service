package com.example.user.exception.specific;

import com.example.user.exception.global.NotFoundException;

import java.util.UUID;

public class BorrowNotFoundException extends NotFoundException {
    public BorrowNotFoundException(UUID borrowId) {
        super("Borrow "+borrowId+" not found");
    }
}