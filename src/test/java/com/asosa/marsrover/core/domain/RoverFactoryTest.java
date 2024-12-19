package com.asosa.marsrover.core.domain;

import com.asosa.marsrover.common.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoverFactoryTest {

    private Platform platform;
    private RoverFactory roverFactory;
    private List<Rover> rovers;

    @BeforeEach
    void setUp() {
        platform = new Platform(10, 10, new ArrayList<>(), false);
        roverFactory = new RoverFactory();
        rovers = new ArrayList<>();
    }

    @Test
    void testNewRoverControl_Success() {
        Rover rover = roverFactory.newRoverControl(2, 3, Direction.NORTH, platform, rovers);

        // Assertions
        assertNotNull(rover, "Rover should not be null");
        assertEquals(2, rover.getPosition().getX(), "Expected X position to be 2");
        assertEquals(3, rover.getPosition().getY(), "Expected Y position to be 3");
        assertEquals(Direction.NORTH, rover.getDirection(), "Expected direction to be NORTH");

        // Add the rover to the list for future checks
        rovers.add(rover);
    }

    @Test
    void testNewRoverControl_PositionOccupied() {
        // Add an existing rover at position (2, 3)
        Rover existingRover = new Rover(2, 3, Direction.NORTH, platform);
        rovers.add(existingRover);

        // Create a new rover at the same starting position
        Rover newRover = roverFactory.newRoverControl(2, 3, Direction.EAST, platform, rovers);

        // Assertions
        assertNotNull(newRover, "Rover should not be null");
        assertNotEquals(existingRover.getPosition(), newRover.getPosition(), "Expected new rover to have a different position");
        rovers.add(newRover);
    }

    @Test
    void testNewRoverControl_NoAvailablePositions() {
        // Fill the platform completely with rovers
        for (int y = 0; y < platform.getHeight(); y++) {
            for (int x = 0; x < platform.getWidth(); x++) {
                rovers.add(new Rover(x, y, Direction.NORTH, platform));
            }
        }

        // Attempt to create a new rover
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                        roverFactory.newRoverControl(0, 0, Direction.NORTH, platform, rovers),
                "Expected exception due to no available positions");

        // Assertion
        assertEquals("No available positions for the rover", exception.getMessage());
    }
}