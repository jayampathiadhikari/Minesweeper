package com.assignment.minesweeper;

import com.assignment.minesweeper.model.Grid;
import com.assignment.minesweeper.view.CliView;
import com.assignment.minesweeper.view.View;

import java.util.Random;

import static java.lang.System.exit;


/**
 * GameController handles user inputs,
 * control the flow of the game based on user actions,
 * and interact with the view and grid.
 */

public class GameController {
    private Grid grid;
    private final View view;
    private final Random random;
    private final UserInputScanner userInputScanner;

    public GameController(View view, UserInputScanner userInputScanner, Random random) {
        this.view = view;
        this.random = random;
        this.userInputScanner = userInputScanner;
    }

    public void initializeGame() {
        view.displayWelcome();

        int gridSize = getValidGridSize();
        int minesCount = getValidMinesCount(gridSize);

        this.grid = new Grid(gridSize, minesCount, random);
        grid.loadGrid();
    }

    public void runGame() {
        while (true) {
            view.displayGrid(grid, false);
            while (!grid.isGameOver() && !grid.isWin()) {
                int[] coordinates = getValidSquareToReveal();

                boolean safeMove = grid.uncoverSquare(coordinates[0], coordinates[1]);
                if (safeMove) {
                    int adjacentMines = grid.getAdjacentMinesCountOfSquare(coordinates[0], coordinates[1]);
                    view.displayMessage("This square contains "+ adjacentMines +" adjacent mines.");
                    view.displayGrid(grid, true);
                } else {
                    view.displayMessage("Oh no, you detonated a mine! Game over.");
                }
            }

            if (grid.isWin()) {
                view.displayGrid(grid, true);
                view.displayMessage("Congratulations, you have won the game!");
            }
            view.displayMessage("Play again ? press (y/n) and then enter... ");

            while (true) {
                String userInput = userInputScanner.getUserInputAsString();
                if (userInput.equalsIgnoreCase("y")) {
                    break;
                } else if (userInput.equalsIgnoreCase("n")){
                    view.displayMessage("Quitting Game...");
                    exit(0);
                }
            }
            resetGame();
        }
    }

    void resetGame() {
        this.grid = null;
        initializeGame();
    }

    private int getValidGridSize() {
        while (true) {
            view.displayMessage("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
            try {
                int gridSize = Integer.parseInt(userInputScanner.getUserInputAsString());
                if (gridSize < 3) {
                    view.displayMessage("Minimum size of grid is 3.");
                } else {
                    if (gridSize > 10) {
                        view.displayMessage("Maximum size of grid is 10.");
                    } else {
                        return gridSize;
                    }
                }
            } catch (NumberFormatException e){
                view.displayMessage("Incorrect input.");
            }
        }
    }

    private int getValidMinesCount(int gridSize) {
        while (true) {
            view.displayMessage("Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
            try {
                int minesCount = Integer.parseInt(userInputScanner.getUserInputAsString());
                int maxMines = (int) (0.35 * gridSize * gridSize);

                if (minesCount > 0 && minesCount <= maxMines) {
                    return minesCount;
                } else if (minesCount == 0){
                    view.displayMessage("There must be at least 1 mine.");
                } else {
                    view.displayMessage("Maximum number is 35% of total squares.");

                }
            } catch (NumberFormatException e) {
                view.displayMessage("Incorrect input.");
            }
        }
    }

    private int[] getValidSquareToReveal() {
        while (true) {
            view.displayMessage("Select a square to reveal (e.g. A1): ");
            try {
                String input = userInputScanner.getUserInputAsString();
                int[] squarePos = Utils.convertStringToIntArray(input);
                if (!(0<=squarePos[0] && squarePos[0] <= grid.getSize()-1) || !(0<=squarePos[1] && squarePos[1] <= grid.getSize()-1)){
                    view.displayMessage("Incorrect input.");
                } else {
                    return squarePos;
                }
            } catch (Exception e) {
                view.displayMessage("Incorrect input.");
            }
        }
    };
}

