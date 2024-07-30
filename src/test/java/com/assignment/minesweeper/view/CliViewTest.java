package com.assignment.minesweeper.view;

import com.assignment.minesweeper.model.Grid;
import org.junit.jupiter.api.Test;
import java.util.Random;

public class CliViewTest {

    @Test
    public void testGridInitialization() {
        Random random = new Random();
        int gridSize = 3;
        Grid grid = new Grid(gridSize, 2, random);
        grid.loadGrid();

        grid.getSquares()[0][0].setUncovered(true);
        grid.getSquares()[0][2].setUncovered(true);
        grid.getSquares()[2][0].setUncovered(true);

        CliView cliView = new CliView();
        cliView.displayGrid(grid);

    }
}
