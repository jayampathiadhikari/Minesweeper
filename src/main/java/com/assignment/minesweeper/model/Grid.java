package com.assignment.minesweeper.model;

public interface Grid {
    void loadGrid();

    int getAdjacentMinesCountOfSquare(int row, int col);

    boolean uncoverSquare(int row, int col);

    boolean isGameOver();

    boolean isWin();

    Square[][] getSquares();

    int getSize();
}
