package com.example.user.exception.specific;

import com.example.user.exception.global.ForbiddenException;

public class MustBeLoginInException extends ForbiddenException {
    public MustBeLoginInException() {
        super("You must be logged in to perform this operation");
    }
}
