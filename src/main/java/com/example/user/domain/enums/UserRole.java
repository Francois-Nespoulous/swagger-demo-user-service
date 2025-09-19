package com.example.user.domain.enums;

public enum UserRole {
    ADMIN,
    USER;

    public boolean canReturnAnyBooks() {
        return this == ADMIN;
    }
}
