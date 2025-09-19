package com.example.user.exception.specific;

import com.example.user.exception.global.NotFoundException;

public class LoggedUserNotFoundException extends NotFoundException {
    public LoggedUserNotFoundException() {
        super("Logged user not found");
    }
}