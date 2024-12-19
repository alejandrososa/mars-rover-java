package com.asosa.marsrover.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void testGetCode() {
        // Assert
        assertEquals("N", Direction.NORTH.getCode(), "NORTH should return 'N'");
        assertEquals("E", Direction.EAST.getCode(), "EAST should return 'E'");
        assertEquals("S", Direction.SOUTH.getCode(), "SOUTH should return 'S'");
        assertEquals("W", Direction.WEST.getCode(), "WEST should return 'W'");
    }

    @Test
    void testFromCode_ValidCodes() {
        // Act & Assert
        assertEquals(Direction.NORTH, Direction.fromCode("N"), "'N' should return NORTH");
        assertEquals(Direction.EAST, Direction.fromCode("E"), "'E' should return EAST");
        assertEquals(Direction.SOUTH, Direction.fromCode("S"), "'S' should return SOUTH");
        assertEquals(Direction.WEST, Direction.fromCode("W"), "'W' should return WEST");
    }

    @Test
    void testFromCode_InvalidCode() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Direction.fromCode("X");
        });

        assertEquals("Invalid direction code: X", exception.getMessage(), "Invalid code should throw an exception");
    }

    @Test
    void testToString() {
        // Act & Assert
        assertEquals("N", Direction.NORTH.toString(), "toString should return 'N' for NORTH");
        assertEquals("E", Direction.EAST.toString(), "toString should return 'E' for EAST");
        assertEquals("S", Direction.SOUTH.toString(), "toString should return 'S' for SOUTH");
        assertEquals("W", Direction.WEST.toString(), "toString should return 'W' for WEST");
    }
}
