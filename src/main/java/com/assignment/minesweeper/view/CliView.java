package com.assignment.minesweeper.view;

import com.assignment.minesweeper.Utils;
import com.assignment.minesweeper.model.Grid;
import com.assignment.minesweeper.model.Square;

public class CliView implements View {

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayWelcome() {
        displayMessage("Welcome to Minesweeper!");
    }

    @Override
    public void displayGrid(Grid grid, boolean updated) {
        if (updated) {
            displayMessage("Here is your updated minefield:");
        } else {
            displayMessage("Here is your minefield:");
        }
        displayGrid(grid);
    }

    void displayGrid(Grid grid) {
        Square[][] squares = grid.getSquares();
        for (int row = -1; row < squares.length; row++) {
            StringBuilder stringBuilder = new StringBuilder();
            if (row == -1) {
                stringBuilder.append(" ");
                for (int col = 0; col < squares.length; col++) {
                    stringBuilder.append(" ").append(col + 1);
                }
            } else {
                stringBuilder.append(Utils.convertToLetter(row));
                for (int col = 0; col < squares.length; col++) {
                    stringBuilder.append(" ");
                    Square sq = squares[row][col];

                    if (sq.isUncovered()) {
                        stringBuilder.append(sq.getAdjacentMines());
                    } else {
                        stringBuilder.append("_");
                    }
                }
            }
            System.out.println(stringBuilder);
        }
    }
}
