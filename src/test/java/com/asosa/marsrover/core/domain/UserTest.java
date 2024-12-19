package com.asosa.marsrover.core.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testNewUser() {
        String username = "testuser";
        User user = new User(username);

        assertNotNull(user, "Expected user to be non-null");
        assertEquals(username, user.getUsername(), "Expected username to match");
        assertNotNull(user.getUuid(), "Expected UUID to be non-empty");
        assertFalse(user.getUuid().isEmpty(), "Expected UUID to be non-empty");
    }

    @Test
    void testGetUUID() {
        User user = new User("test_user");
        String uuid = user.getUuid();

        assertNotNull(uuid, "Expected UUID to be non-null");
        assertFalse(uuid.isEmpty(), "Expected UUID to be non-empty");
        assertEquals(uuid, user.getUuid(), "Expected UUID to match");
    }

    @Test
    void testGetUsername() {
        String username = "test_user";
        User user = new User(username);
        String retrievedUsername = user.getUsername();

        assertEquals(username, retrievedUsername, "Expected username to match");
    }
}