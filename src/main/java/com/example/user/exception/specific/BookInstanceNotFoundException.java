package com.example.user.exception.specific;

import com.example.user.exception.global.NotFoundException;

import java.util.UUID;

public class BookInstanceNotFoundException extends NotFoundException {
    public BookInstanceNotFoundException(UUID bookInstanceId) {
        super("BookInstance "+bookInstanceId+" not found");
    }
}
