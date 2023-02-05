package org.example.MockDemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    UserRepository out = new UserRepository();
    User user1 = new User("John", "345y739008455");
    User user2 = new User("Maximus", "slkdjfglsl");
    User user3 = new User("Anna", "dfg8d08gd8989");
    User user4 = new User("Smith", "345345345344");
    User user5 = new User("Vladimir", "8d09f8gdd");
    User user6 = new User("Anatoliy", "lksdflksg");

    @BeforeEach
    void setUp() {
        out.add(user1);
        out.add(user2);
        out.add(user3);
    }

    @Test
    void getEmptyListOfUsers() {
        UserRepository out = new UserRepository();
        assertTrue(out.getAll().isEmpty());
    }

    @Test
    void getFilledListOfUsers() {
        assertTrue(out.getAll().contains(user1));
        assertTrue(out.getAll().contains(user2));
        assertTrue(out.getAll().contains(user3));
    }

    @Test
    void findByLoginIfUserExist() {
        assertTrue(out.findByLogin(user1.getLogin()).isPresent());
        assertTrue(out.findByLogin(user2.getLogin()).isPresent());
        assertTrue(out.findByLogin(user3.getLogin()).isPresent());
    }

    @Test
    void findByLoginIfUserNotExist(){
        assertFalse(out.findByLogin(user4.getLogin()).isPresent());
        assertFalse(out.findByLogin(user5.getLogin()).isPresent());
        assertFalse(out.findByLogin(user6.getLogin()).isPresent());
    }

    @Test
    void findByLoginAndPasswordIfUserExist(){
        assertTrue(out.findByLoginAndPassword(user1.getLogin(), user1.getPassword()).isPresent());
        assertTrue(out.findByLoginAndPassword(user2.getLogin(), user2.getPassword()).isPresent());
        assertTrue(out.findByLoginAndPassword(user3.getLogin(), user3.getPassword()).isPresent());
    }

    @Test
    void findByLoginAndPasswordIfUserNotExist() {
        assertFalse(out.findByLoginAndPassword(user4.getLogin(), user4.getPassword()).isPresent());
        assertFalse(out.findByLoginAndPassword(user5.getLogin(), user5.getPassword()).isPresent());
        assertFalse(out.findByLoginAndPassword(user6.getLogin(), user6.getPassword()).isPresent());
    }

    @Test
    void findByLoginAndPasswordIfPasswordNotMatch(){
        assertFalse(out.findByLoginAndPassword(user1.getLogin(), user4.getPassword()).isPresent());
        assertFalse(out.findByLoginAndPassword(user2.getLogin(), user5.getPassword()).isPresent());
        assertFalse(out.findByLoginAndPassword(user3.getLogin(), user6.getPassword()).isPresent());
    }
}