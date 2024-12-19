package com.asosa.marsrover.core.domain;

import java.util.UUID;

/**
 * Represents a user with a unique UUID and username.
 */
public class User {
    private final String uuid; // Unique identifier for the user
    private final String username; // Username of the user

    /**
     * Constructor to create a new User with a specific username.
     *
     * @param username The username of the user.
     */
    public User(String username) {
        this.uuid = UUID.randomUUID().toString(); // Generating a new UUID
        this.username = username;
    }

    /**
     * Gets the UUID of the user.
     *
     * @return The UUID.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
}
