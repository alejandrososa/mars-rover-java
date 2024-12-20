package com.asosa.marsrover;

import com.asosa.marsrover.adapters.cli.CommandRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class MarsRoverJavaApplication implements CommandLineRunner {

	private final CommandRegistry commandRegistry;

	public MarsRoverJavaApplication(CommandRegistry commandRegistry) {
		this.commandRegistry = commandRegistry;
	}

	public static void main(String[] args) {
		SpringApplication.run(MarsRoverJavaApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// Display introduction
		showIntro();

		if (args.length == 0) {
			System.out.println("\nNo commands provided. Use '--help' to see available commands.");
			return;
		}

		// Process commands using the CommandRegistry
		String commandName = args[0];
		String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);

		// Find and execute the command
		commandRegistry.findCommand(commandName).ifPresentOrElse(
				command -> command.execute(commandArgs),
				() -> System.out.println("Unknown command: " + commandName + ". Use '--help' to see available commands.")
		);

		// Ensure the application exits after execution
		System.exit(0);
	}

	private void showIntro() {
		System.out.println("==================================");
		System.out.println("  🚀 Mars Rover Mission Control  ");
		System.out.println("==================================");
		System.out.println("Welcome to the Mars Rover CLI!");
		System.out.println("Use this tool to control rovers on the Mars plateau.");
		System.out.println("\n");
		commandRegistry.showAvailableCommands(); // Dynamically list all commands
		System.out.println("\nUse '--help' for more details on each command.");
		System.out.println("==================================\n");
	}
}
