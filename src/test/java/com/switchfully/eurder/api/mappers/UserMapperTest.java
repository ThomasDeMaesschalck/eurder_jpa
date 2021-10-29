package com.switchfully.eurder.api.mappers;

import com.switchfully.eurder.api.dto.users.CreateUserDTO;
import com.switchfully.eurder.api.dto.users.UserDTO;
import com.switchfully.eurder.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private User user;
    private CreateUserDTO userDTO;
    private UserMapper userMapper;


    @BeforeEach
    void setUp() {
        user = User.createUser("firstname", "lastname", "test@test.com", "street 88", "5849894");
        userDTO = new CreateUserDTO( "firstname", "lastname", "email@email.com", "address", "123456");
        userMapper = new UserMapper();
    }

    @Test
    @DisplayName("When converted to DTO all fields are correct")
    void toDTO() {
        UserDTO result = userMapper.toDTO(user);

        assertEquals(user.getId(), result.getId());
        assertEquals(user.getFirstName(), result.getFirstName());
        assertEquals(user.getLastName(), result.getLastName());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getAddress(), result.getAddress());
        assertEquals(user.getRole(), result.getRole());
        assertEquals(user.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    @DisplayName("When converted to entity all fields are correct")
    void toEntity() {
        User result = userMapper.toEntity(userDTO);

        assertEquals(userDTO.getFirstName(), result.getFirstName());
        assertEquals(userDTO.getLastName(), result.getLastName());
        assertEquals(userDTO.getEmail(), result.getEmail());
        assertEquals(userDTO.getAddress(), result.getAddress());
        assertEquals(userDTO.getPhoneNumber(), result.getPhoneNumber());
    }

}
