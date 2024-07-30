package com.assignment.minesweeper;

import com.assignment.minesweeper.model.Grid;
import com.assignment.minesweeper.view.CliView;

import java.util.Random;


/**
 * GameController handles user inputs,
 * control the flow of the game based on user actions,
 * and interact with the view and grid.
 */

public class GameController {
    private Grid grid;
    private final CliView view;
    private final Random random;

    public GameController(CliView view, Random random) {
        this.view = view;
        this.random = random;
    }

    public void initializeGame() {
        view.displayWelcome();
        int gridSize = view.getGridSize();
        int minesCount = view.getMineCount();
        this.grid = new Grid(gridSize, minesCount, random);
        grid.loadGrid();
    }

    public void runGame() {
        while (true) {
            view.displayGrid(grid, false);
            while (!grid.isGameOver() && !grid.isWin()) {
                String input = view.getUserInput("Select a square to reveal (e.g. A1): ");
                int[] coordinates = Utils.convertStringToIntArray(input);

                boolean safeMove = grid.uncoverSquare(coordinates[0], coordinates[1]);
                if (safeMove) {
                    int adjacentMines = grid.getAdjacentMinesCountOfSquare(coordinates[0], coordinates[1]);
                    view.displayMessage("This square contains "+ adjacentMines +" adjacent mines.");
                    view.displayGrid(grid, true);
                } else {
                    view.showGameOver();
                }
            }

            if (grid.isWin()) {
                view.displayGrid(grid, true);
                view.showWin();
            }

            view.getUserInput("Press any key to play again... ");
            resetGame();
        }
    }

    void resetGame() {
        this.grid = null;
        initializeGame();
    }
}

