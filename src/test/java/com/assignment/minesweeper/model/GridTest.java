package com.assignment.minesweeper.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    @Test
    public void testGridInitialization() {
        Random random = new Random();
        int gridSize = 9;
        Grid grid = new Grid(gridSize, 2, random);

        assertNull(grid.getSquares()[0][0]);
        grid.initializeGrid();
        assertInstanceOf(Square.class, grid.getSquares()[0][0]);
    }

    @Test
    public void testGridShouldProperlyBePopulatedWhenMinesAreAtBottom() {
        int gridSize = 4;
        Random mockRandom = Mockito.mock(Random.class);
        Mockito.when(mockRandom.nextInt(gridSize))
                .thenReturn(3,0, 3, 3);

        Grid grid = new Grid(gridSize,2, mockRandom );
        grid.loadGrid();
    }
}
