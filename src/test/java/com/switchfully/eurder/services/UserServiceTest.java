package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.CreateUserDTO;
import com.switchfully.eurder.api.dto.UserDTO;
import com.switchfully.eurder.api.mappers.UserMapper;
import com.switchfully.eurder.domain.entities.User;
import com.switchfully.eurder.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private CreateUserDTO userDTO;
    private CreateUserDTO userDTO2;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userDTO = new CreateUserDTO( "firstname", "lastname", "email@email.com", "address", "123456");
        userDTO2 = new CreateUserDTO( "firstname2", "lastname2", "email2@email.com", "address2", "222222");

        userService = new UserService(new UserMapper(), new UserRepository());
    }

    @Test
    @DisplayName("Saving a user results in user saved in repo")
    void whenSavingTwoUsers_sizeOfRepoIsTwo() {
        userService.saveUser(userDTO);
        userService.saveUser(userDTO2);

        int expected = 2;
        int result = userService.getAllUsers().size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Saving user details are correct")
    void whenSavingUserAndRetrieved_thenFieldsAreCorrect() {
        userService.saveUser(userDTO);

        UserDTO result = userService.getAllUsers().get(0);

        assertNotNull(result.getId());
        assertEquals(userDTO.getFirstName(), result.getFirstName());
        assertEquals(userDTO.getLastName(), result.getLastName());
        assertEquals(userDTO.getAddress(), result.getAddress());
        assertEquals(userDTO.getEmail(), result.getEmail());
        assertEquals(userDTO.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(User.Role.REGISTERED, result.getRole());
    }

}
