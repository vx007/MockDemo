package org.example.MockDemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private final User USER1 = new User("user1", "sdjfksjdlsdfsdfsdsfdsd");
    private final User USER2 = new User("user2", "ldkfjgdsl;s;ldfgkldgls");
    private final User USER3 = new User("user3", "2342342342342342342342");
    private final List<User> LIST_USERS = new ArrayList<>(List.of(USER1, USER2, USER3));

    @Mock
    private UserRepository mock;

    @InjectMocks
    private UserService out;

    @Test
    void getAllLoginsIfListIsEmpty() {
        assertTrue(out.getAllLogins().isEmpty());
    }

    @Test
    void getAllLoginsIfListIsFilled() {
        when(mock.getAll()).thenReturn(LIST_USERS);
        assertFalse(out.getAllLogins().isEmpty());
    }

    @Test
    void findByLoginIfExist() {
        when(mock.findByLogin(anyString())).thenReturn(Optional.of(USER1));
        assertTrue(out.findUserByLogin(USER1.getLogin()));
    }

    @Test
    void findByLoginIfNotExist() {
        when(mock.findByLogin(anyString())).thenReturn(Optional.empty());
        assertFalse(out.findUserByLogin(USER1.getLogin()));
    }

    @Test
    void findByLoginAndPasswordIfExist() {
        when(mock.findByLoginAndPassword(anyString(), anyString())).thenReturn(Optional.of(USER1));
        assertTrue(out.findUserByLoginAndPassword(USER1.getLogin(), USER1.getPassword()));
    }

    @Test
    void findByLoginAndPasswordIfNotExist() {
        when(mock.findByLoginAndPassword(anyString(), anyString())).thenReturn(Optional.empty());
        assertFalse(out.findUserByLoginAndPassword(USER1.getLogin(), USER1.getPassword()));
    }
}
