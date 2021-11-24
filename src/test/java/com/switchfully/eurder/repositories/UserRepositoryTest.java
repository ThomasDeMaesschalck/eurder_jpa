package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@DataJpaTest
@TestPropertySource(properties = "spring.jpa.properties.hibernate.default_schema=eurder")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("Repository is empty when nothing is persisted")
    @Test
    public void whenFindAllUsedOnEmptyRepo_SizeReturnedIsZero() {
        List<User> users = userRepository.findAll().stream().toList();

        Assertions.assertEquals(0, users.size());
    }


    @DisplayName("When saving users they are added to the repository ")
    @Test
    public void whenAddingAMember_repositorySizeIsOne() {
        userRepository.save(new User());

        List<User> users = userRepository.findAll().stream().toList();

        Assertions.assertEquals(1, users.size());
    }

    @DisplayName("Repo size is two when two users are added ")
    @Test
    void whenSavingUsers_thenRepositorySizeIsAccurate() {
        userRepository.save(new User());
        userRepository.save(new User());

        int expected = 2;
        int result = userRepository.findAll().size();

        Assertions.assertEquals(expected, result);
    }

    @DisplayName("Saved user details are found via repository")
    @Test
    void whenSavingUser_thenRepositoryContainsThisUser() {
        userRepository.save(User.builder().firstName("test").build());

        User result = userRepository.findAll().get(0);

        Assertions.assertEquals("test", result.getFirstName());

    }

    @DisplayName("Get by id returns correct User")
    @Test
    void whenGettingById_thenUserIsReturned() {
        userRepository.save(User.builder()
                .firstName("test")
                .build());

        User savedUser = userRepository.findAll().get(0);
        Long idOfUser = savedUser.getId();

        User result = userRepository.getById(idOfUser);

        Assertions.assertEquals(idOfUser, result.getId());
        Assertions.assertEquals("test", result.getFirstName());

    }


}

