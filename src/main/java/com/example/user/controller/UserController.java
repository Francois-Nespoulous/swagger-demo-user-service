package com.example.user.controller;

import com.example.user.domain.model.User;
import com.example.user.dto.in.CreateUserRequest;
import com.example.user.dto.out.UserDto;
import com.example.user.domain.service.UserService;
import com.example.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/logged-user")
    public UserDto getLoggedUser() {
        User user = userService.getLoggedUser();
        return UserMapper.toDto(user);
    }

    @GetMapping("/user/{userId}")
    public UserDto getUser(@PathVariable UUID userId) {
        User user = userService.getUser(userId);
        return UserMapper.toDto(user);
    }

    @GetMapping("/user/username/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return UserMapper.toDto(user);
    }

    @PostMapping("/user/create")
    public UserDto createUser(@RequestBody CreateUserRequest createUserRequest) {
        User user = userService.createUser(createUserRequest);
        return UserMapper.toDto(user);
    }
}
