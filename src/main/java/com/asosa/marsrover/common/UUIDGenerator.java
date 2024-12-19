package com.asosa.marsrover.common;

/**
 * Interface for generating UUIDs.
 */
public interface UUIDGenerator {
    /**
     * Generates a new UUID.
     * @return A string representation of the UUID.
     */
    String generate();
}