package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.entities.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private final Map<UUID, User> users = new HashMap<>();

    public UserRepository() {
    }

    public User save(User user) {
        users.put(user.getId(), user);
        return users.get(user.getId());
    }

    public List<User> getUsers() {
        return List.copyOf(users.values());
    }

    public User getById(UUID userId) {
        User user = users.get(userId);
        if (user == null) {
            throw new IllegalArgumentException("User with id " + userId + " not found.");
        }
        return user;
    }
}
