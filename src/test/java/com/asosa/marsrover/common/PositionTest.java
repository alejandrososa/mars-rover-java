package com.asosa.marsrover.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void testDefaultConstructor() {
        // Arrange
        Position position = new Position();

        // Act & Assert
        assertEquals(0, position.getX(), "Default X should be 0");
        assertEquals(0, position.getY(), "Default Y should be 0");
    }

    @Test
    void testParameterizedConstructor() {
        // Arrange
        Position position = new Position(3, 5);

        // Act & Assert
        assertEquals(3, position.getX(), "X should be 3");
        assertEquals(5, position.getY(), "Y should be 5");
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        Position position = new Position();

        // Act
        position.setX(7);
        position.setY(9);

        // Assert
        assertEquals(7, position.getX(), "X should be 7 after setting");
        assertEquals(9, position.getY(), "Y should be 9 after setting");
    }

    @Test
    void testToString() {
        // Arrange
        Position position = new Position(4, 8);

        // Act
        String result = position.toString();

        // Assert
        assertEquals("(4, 8)", result, "toString should return (4, 8)");
    }

    @Test
    void testEqualsSameObject() {
        // Arrange
        Position position = new Position(2, 3);

        // Act & Assert
        assertTrue(position.equals(position), "A position should be equal to itself");
    }

    @Test
    void testEqualsDifferentObjectSameValues() {
        // Arrange
        Position position1 = new Position(2, 3);
        Position position2 = new Position(2, 3);

        // Act & Assert
        assertTrue(position1.equals(position2), "Two positions with the same values should be equal");
    }

    @Test
    void testEqualsDifferentValues() {
        // Arrange
        Position position1 = new Position(2, 3);
        Position position2 = new Position(4, 5);

        // Act & Assert
        assertFalse(position1.equals(position2), "Two positions with different values should not be equal");
    }

    @Test
    void testEqualsNull() {
        // Arrange
        Position position = new Position(2, 3);

        // Act & Assert
        assertFalse(position.equals(null), "A position should not be equal to null");
    }

    @Test
    void testHashCodeSameValues() {
        // Arrange
        Position position1 = new Position(2, 3);
        Position position2 = new Position(2, 3);

        // Act & Assert
        assertEquals(position1.hashCode(), position2.hashCode(), "Hash codes should be the same for positions with the same values");
    }

    @Test
    void testHashCodeDifferentValues() {
        // Arrange
        Position position1 = new Position(2, 3);
        Position position2 = new Position(3, 4);

        // Act & Assert
        assertNotEquals(position1.hashCode(), position2.hashCode(), "Hash codes should be different for positions with different values");
    }
}
