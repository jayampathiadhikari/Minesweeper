package com.assignment.minesweeper.view;

import com.assignment.minesweeper.model.Grid;

public interface View {
    void printMessage(String message);

    void printGrid(Grid grid);

    void printGrid(Grid grid, boolean revealMines);
}
