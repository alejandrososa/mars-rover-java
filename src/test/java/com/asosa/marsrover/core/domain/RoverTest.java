package com.asosa.marsrover.core.domain;

import com.asosa.marsrover.common.Command;
import com.asosa.marsrover.common.Direction;
import com.asosa.marsrover.common.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    private Platform setupTestPlatform() {
        return new Platform(10, 10, new ArrayList<>(), false);
    }

    private Platform setupTestPlatformWithObstacles() {
        List<Position> obstacles = List.of(new Position(0, 1));
        return new Platform(10, 10, obstacles, false);
    }

    @Test
    void testMove() {
        Platform platform = setupTestPlatform();
        Rover rover = new Rover(0, 0, Direction.NORTH, platform);
        rover.move();
        assertEquals(new Position(0, 1), rover.getPosition(), "Expected position (0, 1)");
    }

    @Test
    void testTurnLeft() {
        Platform platform = setupTestPlatform();
        Rover rover = new Rover(0, 0, Direction.NORTH, platform);
        rover.turnLeft();
        assertEquals(Direction.WEST, rover.getDirection(), "Expected direction W");
    }

    @Test
    void testTurnRight() {
        Platform platform = setupTestPlatform();
        Rover rover = new Rover(0, 0, Direction.NORTH, platform);
        rover.turnRight();
        assertEquals(Direction.EAST, rover.getDirection(), "Expected direction E");
    }

    @Test
    void testMoveAndTurn() {
        Platform platform = setupTestPlatform();
        Rover rover = new Rover(0, 0, Direction.NORTH, platform);
        rover.move();
        rover.turnRight();
        rover.move();
        assertEquals(new Position(1, 1), rover.getPosition(), "Expected position (1, 1)");
        assertEquals(Direction.EAST, rover.getDirection(), "Expected direction E");
    }

    @Test
    void testWrapAround() {
        Platform platform = new Platform(10, 10, new ArrayList<>(), true);
        Rover rover = new Rover(0, 9, Direction.NORTH, platform);
        rover.move();
        assertEquals(new Position(0, 0), rover.getPosition(), "Expected position (0, 0) due to wrap-around");
    }

    @Test
    void testNoWrapAround() {
        Platform platform = setupTestPlatform();
        Rover rover = new Rover(0, 9, Direction.NORTH, platform);
        rover.move();
        assertEquals(new Position(0, 9), rover.getPosition(), "Expected position (0, 9) due to grid boundary");
    }

    @Test
    void testSequentialCommands() {
        Platform platform = setupTestPlatform();
        Rover rover = new Rover(1, 2, Direction.NORTH, platform);

        rover.turnLeft();
        rover.move();
        rover.turnLeft();
        rover.move();
        rover.turnLeft();
        rover.move();
        rover.turnLeft();
        rover.move();
        rover.move();

        assertEquals(new Position(1, 3), rover.getPosition(), "Expected position (1, 3)");
        assertEquals(Direction.NORTH, rover.getDirection(), "Expected direction N");
    }

    @Test
    void testMoveRoversWithCommands() {
        Platform platform = setupTestPlatform();
        Rover rover1 = new Rover(1, 2, Direction.NORTH, platform);
        Rover rover2 = new Rover(3, 3, Direction.EAST, platform);

        // Commands for the first rover
        String commands1 = "LMLMLMLMM";
        for (char cmd : commands1.toCharArray()) {
            rover1.executeCommand(Command.fromCode(String.valueOf(cmd)));
        }

        // Commands for the second rover
        String commands2 = "MMRMMRMRRM";
        for (char cmd : commands2.toCharArray()) {
            rover2.executeCommand(Command.fromCode(String.valueOf(cmd)));
        }

        // Verify the expected outcome for the first rover
        assertEquals(new Position(1, 3), rover1.getPosition(), "Expected rover1 position (1, 3)");
        assertEquals(Direction.NORTH, rover1.getDirection(), "Expected rover1 direction N");

        // Verify the expected outcome for the second rover
        assertEquals(new Position(5, 1), rover2.getPosition(), "Expected rover2 position (5, 1)");
        assertEquals(Direction.EAST, rover2.getDirection(), "Expected rover2 direction E");
    }

    @Test
    void testObstacleEncounter() {
        Platform platform = setupTestPlatformWithObstacles();
        Rover rover = new Rover(0, 0, Direction.NORTH, platform);
        platform.setObstacles(List.of(new Position(0, 1)));

        rover.move();
        assertEquals(new Position(0, 0), rover.getPosition(), "Expected position (0, 0) due to obstacle");
    }

    @Test
    void testSetObstaclesIntegration() {
        Platform platform = new Platform(10, 10, List.of(new Position(1, 0)), false);
        Rover rover = new Rover(0, 0, Direction.NORTH, platform);

        platform.setObstacles(List.of(new Position(0, 1)));
        rover.move();
        assertEquals(new Position(0, 0), rover.getPosition(), "Expected position (0, 0) due to obstacle");
    }

    @Test
    void testInvalidDirection() {
        Platform platform = setupTestPlatform();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Rover(0, 0, Direction.valueOf("INVALID_DIRECTION"), platform));
        assertEquals("No enum constant com.asosa.marsrover.common.Direction.INVALID_DIRECTION", exception.getMessage());
    }

    @Test
    void testInvalidCommand() {
        Platform platform = setupTestPlatform();
        Rover rover = new Rover(0, 0, Direction.NORTH, platform);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                rover.executeCommand(Command.fromCode("X")));
        assertEquals("Invalid command code: X", exception.getMessage());
    }
}
