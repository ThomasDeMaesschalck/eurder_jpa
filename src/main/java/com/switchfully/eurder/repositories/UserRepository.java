package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private final HashMap<UUID, User> users = new HashMap<>();

    public UserRepository() {
    }

    public void save(User user) {
        users.put(user.getId(), user);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(new ArrayList<>(users.values()));
    }

    public User getById(UUID userId) {
        User user = users.get(userId);
        if (user == null) {
            throw new IllegalArgumentException("User with id " + userId + " not found.");
        }
        return user;
    }
}
