package com.example.user.dto.out;

import com.example.user.domain.enums.UserRole;

import java.util.UUID;

public record UserDto(UUID id, String username, int nbOfBooksBorrowed, UserRole userRole) {}
