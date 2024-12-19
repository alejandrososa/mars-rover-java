package com.asosa.marsrover.core.domain;

import com.asosa.marsrover.common.Command;
import com.asosa.marsrover.common.Direction;
import com.asosa.marsrover.common.Position;

/**
 * Interface for controlling a rover's actions and retrieving its state.
 */
public interface RoverControl {

    /**
     * Gets the unique identifier of the rover.
     *
     * @return The UUID of the rover.
     */
    String getUuid();

    /**
     * Moves the rover to the next position based on its current direction.
     */
    void move();

    /**
     * Rotates the rover 90 degrees to the left.
     */
    void turnLeft();

    /**
     * Rotates the rover 90 degrees to the right.
     */
    void turnRight();

    /**
     * Gets the current position of the rover.
     *
     * @return The current position.
     */
    Position getPosition();

    /**
     * Gets the current direction the rover is facing.
     *
     * @return The current direction.
     */
    Direction getDirection();

    /**
     * Sets obstacles on the platform to avoid.
     *
     * @param obstacles A list of positions representing obstacles.
     */
    void setObstacles(Position[] obstacles);

    /**
     * Executes a command for the rover, such as move or rotate.
     *
     * @param command The command to execute.
     * @throws IllegalArgumentException If the command is invalid.
     */
    void executeCommand(Command command);
}