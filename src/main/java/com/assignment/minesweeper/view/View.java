package com.assignment.minesweeper.view;

import com.assignment.minesweeper.model.Grid;

public interface View {
    void displayWelcome();

    void displayGrid(Grid grid, boolean updated);

    void displayMessage(String message);

}
