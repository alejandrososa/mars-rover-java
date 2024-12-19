package com.asosa.marsrover.core.domain;

import com.asosa.marsrover.common.Direction;
import com.asosa.marsrover.common.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlatformTest {

    @Test
    void testIsValidPosition() {
        List<Position> obstacles = List.of(new Position(2, 2));
        Platform platform = new Platform(10, 10, obstacles, false);

        assertTrue(platform.isValidPosition(1, 1), "Expected position (1, 1) to be valid");
        assertFalse(platform.isValidPosition(2, 2), "Expected position (2, 2) to be invalid due to obstacle");
        assertFalse(platform.isValidPosition(10, 10), "Expected position (10, 10) to be invalid due to boundary");
        assertFalse(platform.isValidPosition(-1, 0), "Expected position (-1, 0) to be invalid due to boundary");
    }

    @Test
    void testAddObstacle() {
        Platform platform = new Platform(10, 10, List.of(), false);
        Position newObstacle = new Position(5, 5);
        platform.setObstacles(List.of(newObstacle));

        assertFalse(platform.isValidPosition(5, 5), "Expected position (5, 5) to be invalid due to new obstacle");
    }

    @Test
    void testBoundaryConditions() {
        Platform platform = new Platform(10, 10, List.of(), false);

        // Test edges
        assertTrue(platform.isValidPosition(0, 0), "Expected position (0, 0) to be valid");
        assertTrue(platform.isValidPosition(9, 9), "Expected position (9, 9) to be valid");

        // Test outside boundaries
        assertFalse(platform.isValidPosition(-1, -1), "Expected position (-1, -1) to be invalid due to boundary");
        assertFalse(platform.isValidPosition(10, 10), "Expected position (10, 10) to be invalid due to boundary");
    }

    @Test
    void testRemoveObstacles() {
        List<Position> obstacles = List.of(new Position(1, 1), new Position(2, 2));
        Platform platform = new Platform(10, 10, obstacles, false);

        // Remove all obstacles
        platform.setObstacles(List.of());

        // Ensure previous obstacle positions are now valid
        assertTrue(platform.isValidPosition(1, 1), "Expected position (1, 1) to be valid after removing obstacles");
        assertTrue(platform.isValidPosition(2, 2), "Expected position (2, 2) to be valid after removing obstacles");
    }

    @Test
    void testAllowWrapAroundTrue() {
        Platform platform = new Platform(10, 10, List.of(), true);

        // Test wrap-around on the Y-axis
        Rover rover = new Rover(5, 9, Direction.NORTH, platform);
        rover.move();
        Position position = rover.getPosition();

        assertEquals(0, position.getY(), "Expected position to wrap-around to (5, 0) on Y-axis");
        assertEquals(5, position.getX(), "Expected position to stay at (5, 0) on X-axis");

        // Test wrap-around on the X-axis
        rover = new Rover(9, 5, Direction.EAST, platform);
        rover.move();
        position = rover.getPosition();

        assertEquals(0, position.getX(), "Expected position to wrap-around to (0, 5) on X-axis");
        assertEquals(5, position.getY(), "Expected position to stay at (0, 5) on Y-axis");
    }

    @Test
    void testAllowWrapAroundFalse() {
        Platform platform = new Platform(10, 10, List.of(), false);

        // Test no wrap-around on the X-axis
        assertFalse(platform.isValidPosition(10, 5), "Expected position (10, 5) to not be valid beyond the boundary");

        // Test no wrap-around on the Y-axis
        assertFalse(platform.isValidPosition(5, 10), "Expected position (5, 10) to not be valid beyond the boundary");
    }
}
