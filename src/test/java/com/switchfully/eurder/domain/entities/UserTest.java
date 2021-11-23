/*
package com.switchfully.eurder.domain.entities;

import com.switchfully.eurder.domain.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    @DisplayName("When creating a valid user no exception is thrown")
    void whenValidUserMade_thenDoesNotThrowException() {

        assertDoesNotThrow(() -> User.createUser("Thomas", "Lastname", "thomas@thomas.com", "Fancy address", "+321234598"));
    }

    @Test
    @DisplayName("When creating user with invalid e-mail an exception is thrown")
    void whenUserMadeWithInvalidEmail_thenExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> User.createUser("Thomas", "Lastname", "thomasthomas.com", "Fancy address", "+321234598"));
        assertThrows(IllegalArgumentException.class, () -> User.createUser("Thomas", "Lastname", null, "Fancy address", "+321234598"));
    }

    @Test
    @DisplayName("When creating user with empty firstname an exception is thrown")
    void whenUserMadeWithInvalidFirstname_thenExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> User.createUser("", "Lastname", "thomas@thomas.com", "Fancy address", "+321234598"));
        assertThrows(IllegalArgumentException.class, () -> User.createUser(null, "Lastname", "thomas@thomas.com", "Fancy address", "+321234598"));
    }

    @Test
    @DisplayName("When creating user with empty lastname an exception is thrown")
    void whenUserMadeWithInvalidLastname_thenExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> User.createUser("Thomas", "", "thomas@thomas.com", "Fancy address", "+321234598"));
        assertThrows(IllegalArgumentException.class, () -> User.createUser("Thomas", null, "thomas@thomas.com", "Fancy address", "+321234598"));
    }

    @Test
    @DisplayName("When creating user with empty address an exception is thrown")
    void whenUserMadeWithInvalidAddress_thenExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> User.createUser("Thomas", "Lastname", "thomas@thomas.com", "", "+321234598"));
        assertThrows(IllegalArgumentException.class, () -> User.createUser("Thomas", "Lastname", "thomas@thomas.com", null, "+321234598"));
    }

    @Test
    @DisplayName("When creating user with empty phone an exception is thrown")
    void whenUserMadeWithInvalidPhone_thenExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> User.createUser("Thomas", "Lastname", "thomas@thomas.com", "Fancy address", ""));
        assertThrows(IllegalArgumentException.class, () -> User.createUser("Thomas", "Lastname", "thomas@thomas.com", "Fancy address", null));
    }

    @Test
    @DisplayName("Create member method makes User with proper fields")
    void whenCreateMember_thenUserIsReturned() {
        User result = User.createUser("firstname", "lastname", "test@test.com", "street 55", "564654654");

        String firstname = "firstname";
        String lastname = "lastname";
        String email = "test@test.com";
        String address = "street 55";
        String phonenumber = "564654654";
        User.Role role = User.Role.REGISTERED;

        assertEquals(firstname, result.getFirstName());
        assertEquals(lastname, result.getLastName());
        assertEquals(email, result.getEmail());
        assertEquals(address, result.getAddress());
        assertEquals(phonenumber, result.getPhoneNumber());
        assertEquals(role, result.getRole());
    }

}
*/
