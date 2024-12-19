package com.asosa.marsrover.core.domain;

import com.asosa.marsrover.common.Command;
import com.asosa.marsrover.common.Direction;
import com.asosa.marsrover.common.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MissionControlTest {

    private Platform platform;
    private MissionControl missionControl;

    @BeforeEach
    void setUp() {
        platform = new Platform(10, 10, new ArrayList<>(), false);
        missionControl = new MissionControl(platform);
    }

    @Test
    void testAddRoverInOccupiedPosition() {
        Rover firstRover = new Rover(0, 0, Direction.NORTH, platform);
        missionControl.addRover(firstRover);

        Rover secondRover = new Rover(0, 0, Direction.NORTH, platform);
        Exception exception = assertThrows(IllegalStateException.class, () ->
                missionControl.addRover(secondRover), "Expected exception for adding rover at occupied position");

        assertEquals("Position already occupied", exception.getMessage());
    }

    @Test
    void testAddRover() {
        Rover rover = new Rover(0, 0, Direction.NORTH, platform);
        missionControl.addRover(rover);

        assertEquals(1, missionControl.getRovers().size(), "Expected 1 rover in the mission control");
        assertEquals(rover, missionControl.getRovers().getFirst(), "Expected the rover to be added to mission control");
    }

    @Test
    void testMoveRover() {
        Rover rover = new Rover(0, 0, Direction.NORTH, platform);
        missionControl.addRover(rover);

        missionControl.moveRover(0);

        assertEquals(0, missionControl.getRovers().getFirst().getPosition().getX(), "Expected X position to remain 0");
        assertEquals(1, missionControl.getRovers().getFirst().getPosition().getY(), "Expected Y position to increase by 1");
    }

    @Test
    void testCommandRover() {
        Rover rover = new Rover(0, 0, Direction.NORTH, platform);
        missionControl.addRover(rover);

        missionControl.commandRover(0, Command.MOVE);

        assertEquals(0, missionControl.getRovers().getFirst().getPosition().getX(), "Expected X position to remain 0");
        assertEquals(1, missionControl.getRovers().getFirst().getPosition().getY(), "Expected Y position to increase by 1");
    }

    @Test
    void testCommandRover_InvalidIndex() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () ->
                missionControl.commandRover(1, Command.MOVE), "Expected exception for invalid rover index");

        assertEquals("Rover index out of bounds", exception.getMessage());
    }
}