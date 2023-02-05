package org.example.MockDemo;

import java.util.*;

public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public Collection<User> getAll() {
        return Collections.unmodifiableList(users);
    }

    public Optional<User> findByLogin(String login) {
        return users.stream().filter(u -> u.getLogin().equals(login))
                .findFirst();
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        User user = new User(login, password);
        return users.stream().filter(u -> u.equals(user))
                .findFirst();
    }

    public void add(User user) {
        users.add(user);
    }
}
