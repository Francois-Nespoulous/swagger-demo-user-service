package com.example.user.domain.model;

import com.example.user.domain.enums.UserRole;

import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String password;
    private int nbOfBooksBorrowed;
    private UserRole userRole;

    public User(UUID id, String username, String password, int nbOfBooksBorrowed, UserRole userRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nbOfBooksBorrowed = nbOfBooksBorrowed;
        this.userRole = userRole;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getNbOfBooksBorrowed() {
        return nbOfBooksBorrowed;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}
