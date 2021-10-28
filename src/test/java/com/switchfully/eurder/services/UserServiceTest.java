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
    private UserService userService;

    @BeforeEach
    void setUp() {
        userDTO = new CreateUserDTO("firstname", "lastname", "email@email.com", "address", "123456");

        userService = new UserService(new UserMapper(), new UserRepository());
    }

    @Test
    @DisplayName("Saving a user results in user saved in repo")
    void whenSavingTwoUsers_sizeOfRepoIsTwo() {
        userService.createUser(userDTO);
        UserDTO admin = userService.createAdmin(userDTO);

        int expected = 2;
        int result = userService.getAllUsers(admin.getId()).size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Saving user details are correct")
    void whenSavingUserAndRetrieved_thenFieldsAreCorrect() {
        userService.createUser(userDTO);
        UserDTO admin = userService.createAdmin(userDTO);

        UserDTO result = userService.getAllUsers(admin.getId()).stream().filter(user -> user.getRole().equals(User.Role.REGISTERED)).findFirst().orElseThrow();

        assertNotNull(result.getId());
        assertEquals(userDTO.getFirstName(), result.getFirstName());
        assertEquals(userDTO.getLastName(), result.getLastName());
        assertEquals(userDTO.getAddress(), result.getAddress());
        assertEquals(userDTO.getEmail(), result.getEmail());
        assertEquals(userDTO.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(User.Role.REGISTERED, result.getRole());
    }

    @Test
    @DisplayName("Admin creation role verification")
    void whenSavingAdmin_thenRoleIsAdmin() {
        UserDTO admin = userService.createAdmin(userDTO);

        UserDTO result = userService.getAllUsers(admin.getId()).get(0);

        assertNotNull(result.getId());
        assertEquals(userDTO.getFirstName(), result.getFirstName());
        assertEquals(userDTO.getLastName(), result.getLastName());
        assertEquals(userDTO.getAddress(), result.getAddress());
        assertEquals(userDTO.getEmail(), result.getEmail());
        assertEquals(userDTO.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(User.Role.ADMIN, result.getRole());
    }

    @Test
    @DisplayName("Registered user can't retrieve users list")
    void whenRegisteredUserCallsGetAllUsers_thenExceptionThrown() {
        userService.createUser(userDTO);
        UserDTO admin = userService.createAdmin(userDTO);
        UserDTO result = userService.getAllUsers(admin.getId()).stream().filter(user -> user.getRole().equals(User.Role.REGISTERED)).findFirst().orElseThrow();

        assertThrows(IllegalArgumentException.class, () -> userService.getAllUsers(result.getId()));
    }

    @Test
    @DisplayName("Admin can view users list")
    void whenAdminUserCallsGetAllUsers_theListIsReturned() {
        UserDTO admin = userService.createAdmin(userDTO);

        assertDoesNotThrow(() -> userService.getAllUsers(admin.getId()));
    }

    @Test
    @DisplayName("User type creation system can make admin user")
    void whenAdminNeedsToBeCreated_AdminIsMade() {
        UserDTO admin = userService.createAdmin(userDTO);

        User.Role expected = User.Role.ADMIN;

        assertEquals(expected, admin.getRole());
    }

    @Test
    @DisplayName("User type creation system can make regular user")
    void whenUserNeedsToBeCreated_RegisteredUserIsMade() {
        UserDTO user = userService.createUser(userDTO);

        User.Role expected = User.Role.REGISTERED;

        assertEquals(expected, user.getRole());
    }


    @Test
    @DisplayName("Get by ID works")
    void whenGettingUserById_CorrectUserIsReturned() {
        UserDTO admin = userService.createAdmin(userDTO);
        UserDTO user = userService.createUser(userDTO);

        String expectedLastName = "lastname";

        UserDTO result = userService.getById(admin.getId(), user.getId());

        assertEquals(expectedLastName, result.getLastName());
    }
}
