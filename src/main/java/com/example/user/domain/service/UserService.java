package com.example.user.domain.service;

import com.example.user.client.BorrowClient;
import com.example.user.config.UserDetailsImpl;
import com.example.user.domain.model.User;
import com.example.user.dto.in.CreateUserRequest;
import com.example.user.exception.specific.LoggedUserNotFoundException;
import com.example.user.exception.specific.UserAlreadyExistsException;
import com.example.user.exception.specific.UserNotFoundException;
import com.example.user.mapper.UserMapper;
import com.example.user.persistence.repository.entity.UserEntity;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BorrowClient borrowClient;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BorrowClient borrowClient,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.borrowClient = borrowClient;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return UserMapper.toDomain(userEntity, borrowClient);
    }

    public User getUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return UserMapper.toDomainLight(userEntity);
    }

    public User createUser(CreateUserRequest createUserRequest) {
        User user = UserMapper.toDomain(createUserRequest);

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException(user.getUsername());
        }

        String hashedPassword = passwordEncoder.encode(createUserRequest.password());
        UserEntity userEntity = UserMapper.toEntity(user, hashedPassword);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return UserMapper.toDomain(savedUserEntity, borrowClient);
    }

    /**
     * Only for user controller, call getLoggedUserOptional for other services
     */
    public User getLoggedUser() {
        return this.findLoggedUserOptional()
                .orElseThrow(LoggedUserNotFoundException::new);
    }

    private Optional<User> findLoggedUserOptional() {
        return currentUserDetails()
                .map(UserDetailsImpl::getId)
                .flatMap(userRepository::findById)
                .map(entity -> UserMapper.toDomain(entity, borrowClient));
    }

    private Optional<UserDetailsImpl> currentUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetailsImpl userDetails)
            return Optional.of(userDetails);
        return Optional.empty();
    }
}
