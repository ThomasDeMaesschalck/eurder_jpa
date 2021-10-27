package com.switchfully.eurder.api.mappers;

import com.switchfully.eurder.api.dto.CreateUserDTO;
import com.switchfully.eurder.api.dto.UserDTO;
import com.switchfully.eurder.domain.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress(), user.getPhoneNumber(), user.getRole());
    }

    public List<UserDTO> toDTO(List<User> users) {
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public User toEntity(CreateUserDTO userDTO) {
        return User.createUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getAddress(), userDTO.getPhoneNumber());
    }
}
