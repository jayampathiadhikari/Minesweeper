# Minesweeper

Minesweeper is a command-line based simulation of the classic Minesweeper game. The game prompts the user for grid size and the number of mines, then allows the user to uncover squares on the grid. The objective is to uncover all non-mine squares without detonating any mines.

# How to Run

## Environment Requirements
- Java Development Kit (JDK) 8
- Maven
- Linux ( Tested only on Linux, but should work on Windows/Mac as JVM is designed to be platform independent )

### Prerequisites
Ensure you have Maven installed on your system. You can check by running: `mvn -v`
- If Maven is not installed, you can install it using your package manager, for example:
  `sudo apt-get install maven`

Ensure you have Java installed on your system. You can check by running: `java -version`
- If Java 8 is not installed, you can install it using your package manager, for example:

  `sudo apt-get install openjdk-8-jdk`

### Setup
- Extract the project files from the zipped source code package.
- Open a terminal or command prompt and navigate to the root directory of the project.

### Building the Project
- Navigate to the project directory where pom.xml file is located.
- Run the following Maven command to compile the project and package it into a JAR file:
  `mvn clean package`
- If the build is successful, you should see a `/target` directory with a JAR file named `MineSweeper-1.0-SNAPSHOT.jar`


### Run the Project
- After building the project, navigate to the target directory:
- `cd target`
- Run the JAR file using the java -jar command: `java -jar MineSweeper-1.0-SNAPSHOT.jar`


# Project Structure

## src/
### com/assignment/minesweeper
- **MineSweeperGame.java**: The main entry point of the application. Initializes and starts the game.
  - `main(String[] args)`: Starts the Minesweeper game.


- **GameController.java**: Handles the game logic, manages user inputs, and updates the game state.
  - `GameController(View view, UserInputScanner userInputScanner, Random random)`: Constructor that takes View, UserInputScanner and a Random object as parameters.
  - `initializeGame()`: Initializes the game, prompts user for input, and loads the grid.
  - `runGame()`: Starts the loop to run the game.


- **UserInputScanner.java**: Handles user input from the command line.
   - `getUserInputAsString()`: Prompts the user for input and returns the input as a string.

### com/assignment/minesweeper/model
- **GameGrid.java**: Represents the game grid, including initialization, mine placement, and revealing squares.
  - `GameGrid(int size, int mineCount, Random random)`: Constructor that initializes the grid with the given size and mine count.
  - `loadGrid()`: Loads the grid according to parameters passed when constructing the object. Should call just after object creation.
  - `getAdjacentMinesCountOfSquare(int row, int col)`: Returns the number of adjacent mines for each square.
  - `uncoverSquare(int row, int col)`: Reveals the specified square and recursively reveals adjacent cells if there are no adjacent mines. Returns true if the square is not a mine. Else returns false.
  - `isGameOver()`: Returns true if the game is lost.
  - `isWin()`: Returns true if the game is won.
  - `getSquares()`: Returns Square[][] grid.
  - `getSize()`: Returns the grid size.

   

- **GameSquare.java**: Represents a single square in the grid, including its state (uncovered, mine) and adjacent mine count.
   - `setMine(boolean isMine)`: Sets the square as a mine.
   - `isMine()`: Returns true if the square is a mine.
   - `setAdjacentMines(int adjacentMines)`: Sets the adjacent mine count for the square.
   - `getAdjacentMines()`: Returns the number of adjacent mines.
   - `setUncovered(boolean isUncovered)`: Sets the square as uncovered.
   - `isUncovered()`: Returns true if the square is uncovered.

   

### com/assignment/minesweeper/view
- **CliView.java**: Responsible for displaying the game to the user.
   - `displayWelcome()`: Display the welcome message on console.
   - `displayGrid(Grid grid, boolean updated);`: Display the given grid on console.
   - `displayMessage(String message)`: Display a given message on console.


# Design and Assumptions
- The grid is a square (NxN) with a minimum size of 3x3 and a maximum size of 10x10.
- The number of mines should be at least 1 and at most 35% of the total squares.
- The game starts with user input for grid size and mine count, followed by random mine placement.
- The user selects squares to reveal by entering coordinates (e.g., A1).
- Revealed squares show the number of adjacent mines or trigger the end of the game if a mine is uncovered.
- The game continues until all non-mine squares are uncovered or a mine is revealed.
- Once the game is finished, user can opt to restart or quit.

