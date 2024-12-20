package com.asosa.marsrover.adapters.cli;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Registry for managing and executing console commands.
 * Automatically populated by Spring with all beans implementing ConsoleCommand.
 */
@Component
public class CommandRegistry {

    private final List<ConsoleCommand> commands;

    /**
     * Constructor to inject all available ConsoleCommand beans.
     *
     * @param commands List of all commands detected by Spring's dependency injection.
     */
    public CommandRegistry(List<ConsoleCommand> commands) {
        this.commands = commands;
    }

    /**
     * Finds a specific command by its name.
     *
     * @param name The name of the command to find.
     * @return An Optional containing the matching command if found, or empty if not found.
     */
    public Optional<ConsoleCommand> findCommand(String name) {
        return commands.stream()
                .filter(command -> command.getCommandName().equals(name))
                .findFirst();
    }

    /**
     * Displays all available commands in the registry.
     * Prints the name of each command to the console.
     */
    public void showAvailableCommands() {
        System.out.println("Available commands:");
        commands.forEach(command -> System.out.println("  " + command.getCommandName()));
    }
}
