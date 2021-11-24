package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.users.CreateUserDTO;
import com.switchfully.eurder.api.dto.users.UserDTO;
import com.switchfully.eurder.api.mappers.UserMapper;
import com.switchfully.eurder.domain.entities.User;
import com.switchfully.eurder.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class UserServiceTest {

    private CreateUserDTO userDTO;
    private UserDTO userDTOResponse;
    private UserDTO adminDTOResponse;
    private User user;
    private User adminUser;
    private UserService userService;
    private UserMapper userMapperMock;
    private UserRepository userRepositoryMock;

    @BeforeEach
    void setUp() {
        userDTO = new CreateUserDTO("firstname", "lastname", "email@email.com", "address", "123456");

        userDTOResponse = UserDTO.UserDTOBuilder.item()
                .withFirstName("firstname")
                .withLastName("lastname")
                .withRole(User.Role.REGISTERED).build();

        adminDTOResponse = UserDTO.UserDTOBuilder.item()
                .withFirstName("firstname")
                .withLastName("lastname")
                .withRole(User.Role.ADMIN).build();

        user = User.builder()
                .firstName("firstname")
                .lastName("lastname")
                .role(User.Role.REGISTERED)
                .build();

        adminUser = User.builder()
                .firstName("firstname")
                .lastName("lastname")
                .role(User.Role.ADMIN)
                .build();

        userMapperMock = Mockito.mock(UserMapper.class);
        userRepositoryMock = Mockito.mock(UserRepository.class);

        userService = new UserService(userMapperMock, userRepositoryMock);
    }

    @Test
    @DisplayName("Saving a user results in user saved in repo")
    void whenSavingUser_sizeOfRepoIsOne() {
        userService.createUser(userDTO);

        Mockito.when(userMapperMock.toEntity(any(CreateUserDTO.class))).thenReturn(user);
        Mockito.when(userRepositoryMock.save(any(User.class))).thenReturn(user);
        Mockito.when(userRepositoryMock.findAll()).thenReturn(List.of(user));

        int expected = 1;
        int result = userService.getAllUsers().size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Saving user details are correct")
    void whenSavingUserAndRetrieved_thenFieldsAreCorrect() {
        userService.createUser(userDTO);

        Mockito.when(userRepositoryMock.save(any(User.class))).thenReturn(user);
        Mockito.when(userRepositoryMock.findAll()).thenReturn(List.of(user));
        Mockito.when(userMapperMock.toDTO(any(User.class))).thenReturn(userDTOResponse);

        UserDTO result = userService.getAllUsers().get(0);

        assertEquals(userDTO.getFirstName(), result.getFirstName());
        assertEquals(User.Role.REGISTERED, result.getRole());
    }

    @Test
    @DisplayName("Admin creation role verification")
    void whenSavingAdmin_thenRoleIsAdmin() {
        userService.createAdmin(userDTO);

        Mockito.when(userRepositoryMock.save(any(User.class))).thenReturn(user);
        Mockito.when(userRepositoryMock.findAll()).thenReturn(List.of(user));
        Mockito.when(userMapperMock.toDTO(any(User.class))).thenReturn(adminDTOResponse);

        UserDTO result = userService.getAllUsers().get(0);
        assertEquals(userDTO.getFirstName(), result.getFirstName());
        assertEquals(User.Role.ADMIN, result.getRole());
    }

    @Test
    @DisplayName("Admin can view users list")
    void whenAdminUserCallsGetAllUsers_theListIsReturned() {
        assertDoesNotThrow(() -> userService.getAllUsers());
    }

    @Test
    @DisplayName("User type creation system can make admin user")
    void whenAdminNeedsToBeCreated_AdminIsMade() {
        Mockito.when(userRepositoryMock.save(any(User.class))).thenReturn(user);
        Mockito.when(userRepositoryMock.findAll()).thenReturn(List.of(user));
        Mockito.when(userMapperMock.toAdminEntity(any(CreateUserDTO.class))).thenReturn(adminUser);
        Mockito.when(userMapperMock.toDTO(any(User.class))).thenReturn(adminDTOResponse);

        UserDTO admin =  userService.createAdmin(userDTO);


        User.Role expected = User.Role.ADMIN;

        assertEquals(expected, admin.getRole());
    }

    @Test
    @DisplayName("User type creation system can make regular user")
    void whenUserNeedsToBeCreated_RegisteredUserIsMade() {
        Mockito.when(userRepositoryMock.save(any(User.class))).thenReturn(user);
        Mockito.when(userRepositoryMock.findAll()).thenReturn(List.of(user));
        Mockito.when(userMapperMock.toDTO(any(User.class))).thenReturn(userDTOResponse);
        Mockito.when(userMapperMock.toEntity(any(CreateUserDTO.class))).thenReturn(user);

        UserDTO member =  userService.createUser(userDTO);

        User.Role expected = User.Role.REGISTERED;

        assertEquals(expected, member.getRole());
    }


    @Test
    @DisplayName("Get by ID works")
    void whenGettingUserById_CorrectUserIsReturned() {
        userService.createUser(userDTO);

        String expectedLastName = "lastname";

        Mockito.when(userRepositoryMock.save(any(User.class))).thenReturn(user);
        Mockito.when(userRepositoryMock.getById(any(Long.class))).thenReturn(user);
        Mockito.when(userMapperMock.toDTO(any(User.class))).thenReturn(userDTOResponse);


        UserDTO result = userService.getById(1L);

        assertEquals(expectedLastName, result.getLastName());
    }
}