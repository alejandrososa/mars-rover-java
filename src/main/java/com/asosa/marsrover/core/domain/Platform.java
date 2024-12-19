package com.asosa.marsrover.core.domain;

import com.asosa.marsrover.common.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents the surface on which the rover is moving.
 */
public class Platform {

    private final String uuid;
    private final int width;
    private final int height;
    private List<Position> obstacles;
    private final boolean allowWrapAround;

    /**
     * Creates a new instance of Platform with the given dimensions and obstacles.
     *
     * @param width           The width of the platform.
     * @param height          The height of the platform.
     * @param obstacles       A list of obstacle positions.
     * @param allowWrapAround Determines whether the platform wraps around boundaries.
     */
    public Platform(int width, int height, List<Position> obstacles, boolean allowWrapAround) {
        this.uuid = UUID.randomUUID().toString();
        this.width = width;
        this.height = height;
        this.obstacles = obstacles != null ? new ArrayList<>(obstacles) : new ArrayList<>();
        this.allowWrapAround = allowWrapAround;
    }

    /**
     * Checks if a position (x, y) is valid on the platform.
     *
     * @param x The X-coordinate.
     * @param y The Y-coordinate.
     * @return True if the position is valid; false otherwise.
     */
    public boolean isValidPosition(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }
        for (Position obstacle : obstacles) {
            if (obstacle.getX() == x && obstacle.getY() == y) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given position is occupied by any existing rovers or obstacles.
     *
     * @param position The position to check.
     * @param rovers   The list of rovers on the platform.
     * @return True if the position is occupied; false otherwise.
     */
    public boolean isPositionOccupied(Position position, List<RoverControl> rovers) {
        for (RoverControl rover : rovers) {
            if (rover.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the obstacles on the platform.
     *
     * @param obstacles The new list of obstacles.
     */
    public void setObstacles(List<Position> obstacles) {
        this.obstacles = new ArrayList<>(obstacles);
    }

    /**
     * Gets the UUID of the platform.
     *
     * @return The UUID as a string.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Gets the width of the platform.
     *
     * @return The width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the platform.
     *
     * @return The height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the list of obstacles on the platform.
     *
     * @return The list of obstacles.
     */
    public List<Position> getObstacles() {
        return new ArrayList<>(obstacles);
    }

    /**
     * Checks whether wrap-around is allowed on the platform.
     *
     * @return True if wrap-around is allowed; false otherwise.
     */
    public boolean isAllowWrapAround() {
        return allowWrapAround;
    }
}

