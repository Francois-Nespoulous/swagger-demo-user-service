package com.example.user.exception.specific;

import com.example.user.exception.global.ForbiddenException;

public class UserNotAllowedToReturnBookException extends ForbiddenException {
    public UserNotAllowedToReturnBookException(String usernameWantToReturn) {
        super("User " + usernameWantToReturn + " not allowed to return this book");
    }
}