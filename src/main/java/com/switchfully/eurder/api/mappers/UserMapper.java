package com.switchfully.eurder.api.mappers;

import com.switchfully.eurder.api.dto.users.CreateUserDTO;
import com.switchfully.eurder.api.dto.users.UserDTO;
import com.switchfully.eurder.domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        return new UserDTO.UserDTOBuilder()
                .withId(user.getId())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withEmail(user.getEmail())
                .withPhoneNumber(user.getPhoneNumber())
                .withAddress(user.getAddress())
                .withRole(user.getRole())
                .build();
    }

    public User toEntity(CreateUserDTO userDTO) {
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .phoneNumber(userDTO.getPhoneNumber())
                .role(User.Role.REGISTERED)
                .build();
    }

    public User toAdminEntity(CreateUserDTO userDTO) {
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .phoneNumber(userDTO.getPhoneNumber())
                .role(User.Role.ADMIN)
                .build();
    }
}
