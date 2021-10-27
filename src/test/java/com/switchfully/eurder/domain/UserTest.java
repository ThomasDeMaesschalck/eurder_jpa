package com.switchfully.eurder.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    @DisplayName("When creating a valid user no exception is thrown")
    void whenValidUserMade_thenDoesNotThrowException(){
        assertDoesNotThrow(() -> new User("Thomas", "Lastname", "thomas@thomas.com", "Fancy address", "+321234598"));
    }

    @Test
    @DisplayName("When creating user with invalid e-mail an exception is thrown")
    void whenUserMadeWithInvalidEmail_thenExceptionThrown(){
        assertThrows(IllegalArgumentException.class, () -> new User("Thomas", "Lastname", "thomasthomas.com", "Fancy address", "+321234598"));
    }

}