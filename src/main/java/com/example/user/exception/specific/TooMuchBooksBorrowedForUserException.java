package com.example.user.exception.specific;

import com.example.user.exception.global.ConflictException;

public class TooMuchBooksBorrowedForUserException extends ConflictException {
    public TooMuchBooksBorrowedForUserException(String username) {
        super("Too much books nbBorrowedBooks for user "+username);
    }
}