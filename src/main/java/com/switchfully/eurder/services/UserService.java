package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.users.CreateUserDTO;
import com.switchfully.eurder.api.dto.users.UserDTO;
import com.switchfully.eurder.api.mappers.UserMapper;
import com.switchfully.eurder.domain.entities.User;
import com.switchfully.eurder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private boolean adminExists;

    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserDTO createUser(CreateUserDTO userDTO) {
        User created = userRepository.save(userMapper.toEntity(userDTO));
        return userMapper.toDTO(created);
    }

    public UserDTO createAdmin(CreateUserDTO adminToCreate) {
        if (adminExists) {
            throw new IllegalArgumentException("There can be only one admin");
        }
        User created = userRepository.save(userMapper.toAdminEntity(adminToCreate));
        adminExists = true;
        return userMapper.toDTO(created);
    }

    public List<UserDTO> getAllUsers(Long adminId) {
        assertAdminId(adminId);

        return userRepository.findAll().stream()
                .sorted(Comparator.comparing(User::getLastName).thenComparing(User::getFirstName))
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getById(Long adminId, Long userId) {
        assertAdminId(adminId);

        return userMapper.toDTO(userRepository.findById(userId).get());
    }

    public void assertAdminId(Long adminId) {
        Optional<User> user = userRepository.findById(adminId);
        if (user.get().getRole() != User.Role.ADMIN) {
            throw new IllegalArgumentException("Unauthorized user");
        }
    }

    public void assertUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("Invalid user id");
        }
    }

}
