/*
package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryTest {

    private UserRepository userRepository;
    private User user1;
    private User user2;

    String firstname;
    String lastname;
    String email;
    String address;
    String phonenumber;
    User.Role role;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        user1 = User.createUser("firstname", "lastname", "test@test.com", "street 88", "5849894");
        user2 = User.createUser("firstname2", "lastname2", "test2@test.com", "street 99", "111222");

        firstname = "firstname";
        lastname = "lastname";
        email = "test@test.com";
        address = "street 88";
        phonenumber = "5849894";
        role = User.Role.REGISTERED;
    }

    @DisplayName("When saving users they are added to the repository ")
    @Test
    void whenSavingUsers_thenRepositorySizeIsAccurate() {
        userRepository.save(user1);
        userRepository.save(user2);

        int expected = 2;
        int result = userRepository.getUsers().size();

        assertEquals(expected, result);
    }

    @DisplayName("Saved user details are found via repository")
    @Test
    void whenSavingUser_thenRepositoryContainsThisUser() {
        userRepository.save(user1);

        User result = userRepository.getUsers().get(0);

        assertEquals(firstname, result.getFirstName());
        assertEquals(lastname, result.getLastName());
        assertEquals(email, result.getEmail());
        assertEquals(address, result.getAddress());
        assertEquals(phonenumber, result.getPhoneNumber());
        assertEquals(role, result.getRole());
    }

    @DisplayName("Get by id returns correct User")
    @Test
    void whenGettingById_thenUserIsReturned() {
        userRepository.save(user1);

        User result = userRepository.getUsers().get(0);
        UUID idOfUser = result.getId();

        result = userRepository.getById(idOfUser);

        assertEquals(firstname, result.getFirstName());
        assertEquals(lastname, result.getLastName());
        assertEquals(email, result.getEmail());
        assertEquals(address, result.getAddress());
        assertEquals(phonenumber, result.getPhoneNumber());
        assertEquals(role, result.getRole());
    }
}
*/
