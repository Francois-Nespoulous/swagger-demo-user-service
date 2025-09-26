package com.example.user.mapper;

import com.example.user.client.BorrowClient;
import com.example.user.domain.enums.UserRole;
import com.example.user.domain.model.User;
import com.example.user.dto.in.CreateUserRequest;
import com.example.user.dto.out.UserDto;
import com.example.user.persistence.repository.entity.UserEntity;

public class UserMapper {
    public static User toDomain(UserEntity userEntity, BorrowClient borrowClient) {
        int nbOfBooksBorrowed = borrowClient.countBooksBorrowedByUser(userEntity.getId());
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                nbOfBooksBorrowed,
                userEntity.getUserRole()
        );
    }

    public static User toDomainLight(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                0,
                userEntity.getUserRole()
        );
    } //TODO UserDto user != UserDto borrow ! user s'en fout du nombre de livre empruntés

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getNbOfBooksBorrowed(),
                user.getUserRole()
        );
    }

    public static User toDomain(CreateUserRequest createUserRequest) {
        return new User(
                null,
                createUserRequest.username(),
                null,
                0,
                UserRole.USER
        );
    }

    public static UserEntity toEntity(User user, String hashedPassword) {
        return new UserEntity(
                user.getId(),
                user.getUsername(),
                hashedPassword,
                user.getUserRole()
        );
    }

    public static User toDomain(UserDto userDto) {
        return new User(
                userDto.id(),
                userDto.username(),
                null,
                userDto.nbOfBooksBorrowed(),
                userDto.userRole()
        );
    }
}
