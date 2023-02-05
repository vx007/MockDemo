package org.example.MockDemo;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAllLogins() {
        List<String> result = new ArrayList<>();

        userRepository.getAll().stream()
                .filter(u -> !result.contains(u.getLogin()))
                .forEach(u -> result.add(u.getLogin()));

        return result;
    }

    public void addUser(String login, String password) {
        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (getAllLogins().contains(login)) {
            throw new UserNonUniqueException("Login is exist!");
        }

        userRepository.add(new User(login, password));
    }

    public boolean findUserByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password).isPresent();
    }

    public boolean findUserByLogin(String login) {
        return userRepository.findByLogin(login).isPresent();
    }
}
