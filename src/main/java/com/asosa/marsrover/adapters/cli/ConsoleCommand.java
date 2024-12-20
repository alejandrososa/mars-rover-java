package com.asosa.marsrover.adapters.cli;

public interface ConsoleCommand {
    /**
     * Execute the command with the provided arguments.
     *
     * @param args Command arguments.
     */
    void execute(String[] args);

    /**
     * Returns the name of the command.
     *
     * @return Name of the command.
     */
    String getCommandName();
}
