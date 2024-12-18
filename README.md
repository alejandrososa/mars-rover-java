```markdown
# Mars Rover Java

Mars Rover Java is a project simulating a Mars rover explorer using Java and Spring Boot with hexagonal architecture (Ports and Adapters). The project focuses on controlling the rover's movements and direction within a grid, showcasing modular software design. Key technologies include Java, RESTful APIs, and principles of clean architecture.

## Learning Competencies
- Understand and implement object-oriented design, DDD, patterns, and best practices.
- Implement tests using TDD.
- Properly handle input and output operations.

## The Challenge
NASA has deployed a fleet of robotic rovers on a rectangular plateau on Mars. Each rover's state consists of its position (X, Y) and the compass direction (N, S, E, W). Your task is to develop an API that controls the rovers' movements across a 10x10 grid, interpreting commands (`L`, `R`, `M`), and handling possible obstacles.

## Input
- The plateau is represented as a 10x10 grid.
- Initial positions and commands for each rover.

## Output
- Final coordinates and direction of each rover.
- If an obstacle is encountered, prefix the output with `O:`.

## Example Input
```
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```

## Expected Output
```
1 3 N
5 1 E
```

## Mars Rover Project Execution

### 1. **Preparation**

1. Clone the repository:
   ```sh
   git clone https://github.com/alejandrososa/mars-rover-java.git
   cd mars-rover-java
   ```

2. Build the project using Gradle:
   ```sh
   ./gradlew build
   ```

### 2. **Running the Application**

1. Start the Spring Boot application:
   ```sh
   ./gradlew bootRun
   ```

2. Access the API at [http://localhost:8080](http://localhost:8080).

### 3. **Using the API**

- **HTTP API**: Send a POST request to `/rover/move` with a JSON payload to control the rovers.

Example Request:
```json
{
  "x": 1,
  "y": 2,
  "direction": "N",
  "commands": "LMLMLMLMM"
}
```

Example Response:
```json
{
  "x": 1,
  "y": 3,
  "direction": "N"
}
```

## Running the Tests

1. **Run the tests**:
   ```sh
   ./gradlew test
   ```

## Assumptions
- The grid starts at (0, 0) in the bottom-left corner.
- Valid movements are: `L`, `R`, `M`.
- Valid compass directions are: `N`, `S`, `E`, `W`.
- Exception handling for moves outside the grid bounds or invalid inputs.

## Using the Makefile

To simplify the setup and execution of the project, you can use the provided Makefile:

### 1. **Setup the environment**

To prepare the environment and install dependencies, run:
   ```sh
   make setup
   ```

### 2. **Build the project**

To compile the project, use:
   ```sh
   make build
   ```

### 3. **Start the application**

To start the Spring Boot server on port 8080, execute:
   ```sh
   make start
   ```

### 4. **Help**

For more information about available commands and usage, run:
   ```sh
   make help
   ```

Please refer to this README file for detailed instructions on how to use the project and understand its features.
```