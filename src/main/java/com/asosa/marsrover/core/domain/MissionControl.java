package com.asosa.marsrover.core.domain;

import com.asosa.marsrover.common.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents the mission control managing the platform and its rovers.
 */
public class MissionControl {
    private final String uuid; // Unique identifier for the mission control
    private final Platform platform; // Platform on which the rovers operate
    private final List<Rover> rovers; // List of rovers under mission control

    /**
     * Constructor to create a new MissionControl instance.
     *
     * @param platform The platform associated with the mission control.
     */
    public MissionControl(Platform platform) {
        this.uuid = UUID.randomUUID().toString();
        this.platform = platform;
        this.rovers = new ArrayList<>();
    }

    /**
     * Gets the unique identifier for the mission control.
     *
     * @return The UUID of the mission control.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Adds a new rover to the mission control.
     *
     * @param rover The rover to be added.
     * @throws IllegalStateException If the position of the rover is already occupied.
     */
    public void addRover(Rover rover) {
        for (Rover r : rovers) {
            if (r.getPosition().equals(rover.getPosition())) {
                throw new IllegalStateException("Position already occupied");
            }
        }
        rovers.add(rover);
    }

    /**
     * Moves a specified rover forward.
     *
     * @param index The index of the rover in the list.
     * @throws IndexOutOfBoundsException If the rover index is out of bounds.
     */
    public void moveRover(int index) {
        if (index < 0 || index >= rovers.size()) {
            throw new IndexOutOfBoundsException("Rover index out of bounds");
        }
        rovers.get(index).move();
    }

    /**
     * Sends a command to the specified rover.
     *
     * @param index   The index of the rover in the list.
     * @param command The command to be executed by the rover.
     * @throws IndexOutOfBoundsException If the rover index is out of bounds.
     */
    public void commandRover(int index, Command command) {
        if (index < 0 || index >= rovers.size()) {
            throw new IndexOutOfBoundsException("Rover index out of bounds");
        }
        rovers.get(index).executeCommand(command);
    }

    /**
     * Gets the platform associated with the mission control.
     *
     * @return The platform.
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * Gets the list of rovers under mission control.
     *
     * @return The list of rovers.
     */
    public List<Rover> getRovers() {
        return rovers;
    }
}
