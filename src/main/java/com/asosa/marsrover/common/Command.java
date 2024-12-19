package com.asosa.marsrover.common;

/**
 * Enum to represent the valid commands for controlling a rover.
 */
public enum Command {
    MOVE("M"),  // Command to move forward
    LEFT("L"),  // Command to turn left
    RIGHT("R"); // Command to turn right

    private final String code;

    /**
     * Constructor for the Command enum.
     * @param code The string representation of the command.
     */
    Command(String code) {
        this.code = code;
    }

    /**
     * Gets the string code of the command.
     * @return The command code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Converts a string code into a Command.
     * @param code The string representation of the command.
     * @return The corresponding Command enum.
     * @throws IllegalArgumentException If the code is not valid.
     */
    public static Command fromCode(String code) {
        for (Command command : values()) {
            if (command.code.equals(code)) {
                return command;
            }
        }
        throw new IllegalArgumentException("Invalid command code: " + code);
    }
}


