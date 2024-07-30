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

    public GameController(CliView view, UserInputScanner userInputScanner, Random random) {
        this.view = view;
        this.random = random;
        this.userInputScanner = userInputScanner;
    }

    public void initializeGame() {
        view.displayWelcome();

        view.displayMessage("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
        int gridSize = userInputScanner.getUserInputAsInt();

        view.displayMessage("Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
        int minesCount = userInputScanner.getUserInputAsInt();

        this.grid = new Grid(gridSize, minesCount, random);
        grid.loadGrid();
    }

    public void runGame() {
        while (true) {
            view.displayGrid(grid, false);
            while (!grid.isGameOver() && !grid.isWin()) {
                view.displayMessage("Select a square to reveal (e.g. A1): ");
                String input = userInputScanner.getUserInputAsString();
                int[] coordinates = Utils.convertStringToIntArray(input);

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
}

