package com.asosa.marsrover.adapters.cli.dto;

import com.asosa.marsrover.common.Position;
import com.asosa.marsrover.common.Direction;

import java.util.List;

/**
 * DTO representing the request to create a Mission Control.
 */
public class CreateMissionControlRequest {

    private String username;
    private PlatformDimensions platform;
    private List<RoverInitialization> rovers;
    private boolean allowWrapAround;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PlatformDimensions getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformDimensions platform) {
        this.platform = platform;
    }

    public List<RoverInitialization> getRovers() {
        return rovers;
    }

    public void setRovers(List<RoverInitialization> rovers) {
        this.rovers = rovers;
    }

    public boolean isAllowWrapAround() {
        return allowWrapAround;
    }

    public void setAllowWrapAround(boolean allowWrapAround) {
        this.allowWrapAround = allowWrapAround;
    }

    /**
     * Represents the platform dimensions (width and height).
     */
    public static class PlatformDimensions {
        private int width;
        private int height;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    /**
     * Represents the initialization details for a rover.
     */
    public static class RoverInitialization {
        private Position initialPosition;
        private Direction direction;

        // Getters and setters
        public Position getInitialPosition() {
            return initialPosition;
        }

        public void setInitialPosition(Position initialPosition) {
            this.initialPosition = initialPosition;
        }

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }
    }

    @Override
    public String toString() {
        return "CreateMissionControlRequest{" +
                "username='" + username + '\'' +
                ", width=" + platform.getWidth() +
                ", height=" + platform.getHeight() +
                ", rovers='" + rovers.size() + '\'' +
                ", allowWrapAround=" + allowWrapAround +
                '}';
    }
}
