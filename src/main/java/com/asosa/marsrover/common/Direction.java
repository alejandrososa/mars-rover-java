package com.asosa.marsrover.common;

/**
 * Enum to represent the cardinal directions for a rover.
 */
public enum Direction {
    NORTH("N"), // North direction
    EAST("E"),  // East direction
    SOUTH("S"), // South direction
    WEST("W");  // West direction

    private final String code;

    /**
     * Constructor for the Direction enum.
     * @param code The string representation of the direction.
     */
    Direction(String code) {
        this.code = code;
    }

    /**
     * Gets the string code of the direction.
     * @return The direction code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Converts a string code into a Direction.
     * @param code The string representation of the direction.
     * @return The corresponding Direction enum.
     * @throws IllegalArgumentException If the code is not valid.
     */
    public static Direction fromCode(String code) {
        for (Direction direction : values()) {
            if (direction.code.equals(code)) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Invalid direction code: " + code);
    }

    /**
     * Returns the code instead of the enum name for the toString method.
     * @return The code of the direction.
     */
    @Override
    public String toString() {
        return code;
    }
}
