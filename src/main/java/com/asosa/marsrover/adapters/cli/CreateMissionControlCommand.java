package com.asosa.marsrover.adapters.cli;

import com.asosa.marsrover.adapters.cli.dto.CreateMissionControlRequest;
import com.asosa.marsrover.adapters.cli.dto.CreateMissionControlRequest.PlatformDimensions;
import com.asosa.marsrover.adapters.cli.dto.CreateMissionControlRequest.RoverInitialization;
import com.asosa.marsrover.common.Direction;
import com.asosa.marsrover.common.Position;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateMissionControlCommand implements ConsoleCommand {

    @Override
    public void execute(String[] args) {
        try {
            // Parse arguments into a DTO
            CreateMissionControlRequest request = parseArguments(args);

            // Execute the command logic
            System.out.println("Creating Mission Control with:");
            System.out.println(request);

        } catch (IllegalArgumentException e) {
            System.out.println("Error parsing arguments: " + e.getMessage());
            showHelp();
        }
    }

    @Override
    public String getCommandName() {
        return "create-mission-control";
    }

    /**
     * Parses console arguments and creates a CreateMissionControlRequest DTO.
     *
     * @param args Command-line arguments.
     * @return A populated CreateMissionControlRequest instance.
     * @throws IllegalArgumentException If any argument is invalid or missing.
     */
    private CreateMissionControlRequest parseArguments(String[] args) {
        String username = null;
        int width = 0, height = 0;
        List<RoverInitialization> rovers = new ArrayList<>();
        boolean wrapAround = false;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--username":
                case "-u":
                    username = cleanArgument(args[++i]);
                    break;
                case "--width":
                case "-w":
                    width = Integer.parseInt(cleanArgument(args[++i]));
                    break;
                case "--height":
                case "-h":
                    height = Integer.parseInt(cleanArgument(args[++i]));
                    break;
                case "--rovers":
                case "-r":
                    String[] roverDetails = cleanArgument(args[++i]).split(";");
                    for (String roverDetail : roverDetails) {
                        String[] parts = roverDetail.split(",");
                        if (parts.length != 3) {
                            throw new IllegalArgumentException("Invalid rover format. Expected format: x,y,direction");
                        }
                        int x = Integer.parseInt(parts[0]);
                        int y = Integer.parseInt(parts[1]);
                        Direction direction = Direction.fromCode(parts[2]);
                        rovers.add(createRoverInitialization(x, y, direction));
                    }
                    break;
                case "--wrap":
                    wrapAround = true;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown argument: " + args[i]);
            }
        }

        // Validate required fields
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username is required.");
        }
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be greater than zero.");
        }

        // Build and return the DTO
        CreateMissionControlRequest request = new CreateMissionControlRequest();
        request.setUsername(username);

        PlatformDimensions platform = new PlatformDimensions();
        platform.setWidth(width);
        platform.setHeight(height);
        request.setPlatform(platform);

        request.setRovers(rovers);
        request.setAllowWrapAround(wrapAround);

        return request;
    }

    /**
     * Creates a RoverInitialization DTO from parsed inputs.
     *
     * @param x         X-coordinate of the rover.
     * @param y         Y-coordinate of the rover.
     * @param direction Initial direction of the rover.
     * @return A RoverInitialization instance.
     */
    private RoverInitialization createRoverInitialization(int x, int y, Direction direction) {
        RoverInitialization rover = new RoverInitialization();
        rover.setInitialPosition(new Position(x, y));
        rover.setDirection(direction);
        return rover;
    }

    /**
     * Cleans a given argument by removing surrounding quotes if they exist.
     *
     * @param arg The argument to clean.
     * @return The cleaned argument.
     */
    private String cleanArgument(String arg) {
        if (arg.startsWith("\"") && arg.endsWith("\"")) {
            return arg.substring(1, arg.length() - 1);
        }
        return arg;
    }

    /**
     * Displays help information for this command.
     */
    private void showHelp() {
        System.out.println("Usage:");
        System.out.println("  create-mission-control [options]");
        System.out.println("Options:");
        System.out.println("  -u, --username <username>       Username for the mission control.");
        System.out.println("  -w, --width <width>             Width of the platform.");
        System.out.println("  -h, --height <height>           Height of the platform.");
        System.out.println("  -r, --rovers <rovers>           Semicolon-separated list of rovers in the format x,y,direction.");
        System.out.println("                                   Example: 1,2,N;3,3,E");
        System.out.println("  --wrap                          Enable wrap-around.");
        System.out.println();
        System.out.println("Example:");
        System.out.println("  create-mission-control -u user1 -w 10 -h 10 -r \"1,2,N;3,3,E\" --wrap");
    }
}