package com.asosa.marsrover.core.domain;

import com.asosa.marsrover.common.Command;
import com.asosa.marsrover.common.Direction;
import com.asosa.marsrover.common.Position;

import java.util.UUID;

/**
 * Represents a Rover with a unique ID, position, direction, and associated platform.
 */
public class Rover {
    private final String uuid;
    private Position position;
    private Direction direction;
    private Platform platform;

    /**
     * Constructs a new Rover with the specified position, direction, and platform.
     *
     * @param x         The initial X-coordinate.
     * @param y         The initial Y-coordinate.
     * @param direction The initial direction.
     * @param platform  The platform on which the rover operates.
     * @throws IllegalArgumentException If the direction is invalid.
     */
    public Rover(int x, int y, Direction direction, Platform platform) {
        if (direction == null) {
            throw new IllegalArgumentException("Invalid direction");
        }
        this.uuid = UUID.randomUUID().toString();
        this.position = new Position(x, y);
        this.direction = direction;
        this.platform = platform;
    }

    /**
     * Moves the rover in the current direction.
     */
    public void move() {
        if (platform == null) {
            return;
        }

        int nextX = position.getX();
        int nextY = position.getY();

        switch (direction) {
            case NORTH -> nextY++;
            case EAST -> nextX++;
            case SOUTH -> nextY--;
            case WEST -> nextX--;
        }

        if (platform.isAllowWrapAround()) {
            // Wrap-around logic
            nextX = (nextX + platform.getWidth()) % platform.getWidth();
            nextY = (nextY + platform.getHeight()) % platform.getHeight();
        } else {
            // Boundary restriction without wrap-around
            if (nextX < 0 || nextX >= platform.getWidth() || nextY < 0 || nextY >= platform.getHeight()) {
                return;
            }
        }

        // Check for obstacles
        if (!platform.isValidPosition(nextX, nextY)) {
            return;
        }

        // Update the position
        position = new Position(nextX, nextY);
    }

    /**
     * Turns the rover to the left.
     */
    public void turnLeft() {
        switch (direction) {
            case NORTH -> direction = Direction.WEST;
            case WEST -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.EAST;
            case EAST -> direction = Direction.NORTH;
        }
    }

    /**
     * Turns the rover to the right.
     */
    public void turnRight() {
        switch (direction) {
            case NORTH -> direction = Direction.EAST;
            case EAST -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.WEST;
            case WEST -> direction = Direction.NORTH;
        }
    }

    /**
     * Executes a command on the rover.
     *
     * @param command The command to execute.
     * @throws IllegalArgumentException If the command is invalid.
     */
    public void executeCommand(Command command) {
        switch (command) {
            case MOVE -> move();
            case LEFT -> turnLeft();
            case RIGHT -> turnRight();
            default -> throw new IllegalArgumentException("Invalid command");
        }
    }

    public String getUuid() {
        return uuid;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
