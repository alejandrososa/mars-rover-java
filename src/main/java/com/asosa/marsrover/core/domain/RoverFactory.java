package com.asosa.marsrover.core.domain;

import com.asosa.marsrover.common.Direction;
import com.asosa.marsrover.common.Position;

import java.util.List;

/**
 * Factory class to create instances of RoverControl.
 */
public class RoverFactory {

    /**
     * Creates a new instance of RoverControl. If the position is occupied, it searches for the next available position.
     *
     * @param x        The initial X-coordinate for the rover.
     * @param y        The initial Y-coordinate for the rover.
     * @param direction The direction the rover is facing.
     * @param platform The platform on which the rover operates.
     * @param rovers   A list of existing rovers on the platform.
     * @return A new Rover instance.
     * @throws IllegalStateException If no available positions are found for the rover.
     */
    public Rover newRoverControl(int x, int y, Direction direction, Platform platform, List<Rover> rovers) {
        Position position = new Position(x, y);

        // Search for a free position on the platform
        while (isPositionOccupied(position, platform, rovers)) {
            // Move to the next position
            if (position.getX() < platform.getWidth() - 1) {
                position.setX(position.getX() + 1);
            } else if (position.getY() < platform.getHeight() - 1) {
                position.setX(0);
                position.setY(position.getY() + 1);
            } else {
                // If all possible positions are checked and none are free
                throw new IllegalStateException("No available positions for the rover");
            }
        }

        // Create a new rover
        return new Rover(position.getX(), position.getY(), direction, platform);
    }

    /**
     * Checks if a position is occupied by any rover or obstacle.
     *
     * @param position The position to check.
     * @param platform The platform on which the position is being checked.
     * @param rovers   The list of existing rovers.
     * @return True if the position is occupied; otherwise, false.
     */
    private boolean isPositionOccupied(Position position, Platform platform, List<Rover> rovers) {
        for (Rover rover : rovers) {
            if (rover.getPosition().equals(position)) {
                return true;
            }
        }
        return !platform.isValidPosition(position.getX(), position.getY());
    }
}

