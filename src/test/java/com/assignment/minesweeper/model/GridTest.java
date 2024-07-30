package com.assignment.minesweeper.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

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
    public void testPlaceMinesForSmallGrid() {
        Random random = new Random();
        int gridSize = 4;
        Grid grid = new Grid(gridSize, 2, random);

        Random mockRandom = Mockito.mock(Random.class);
        Mockito.when(mockRandom.nextInt(gridSize))
                .thenReturn(3,0, 3, 3);
        grid.initializeGrid();

        assertFalse(grid.getSquares()[3][0].isMine());
        assertFalse(grid.getSquares()[3][3].isMine());

        grid.placeMines(mockRandom);

        assertTrue(grid.getSquares()[3][0].isMine());
        assertTrue(grid.getSquares()[3][3].isMine());
    }

    @Test
    public void testPlaceMinesForLargeGrid() {
        Random random = new Random();
        int gridSize = 9;
        Grid grid = new Grid(gridSize, 2, random);

        Random mockRandom = Mockito.mock(Random.class);
        Mockito.when(mockRandom.nextInt(gridSize))
                .thenReturn(3,0, 3, 3);
        grid.initializeGrid();

        assertFalse(grid.getSquares()[3][0].isMine());
        assertFalse(grid.getSquares()[3][3].isMine());

        grid.placeMines(mockRandom);

        assertTrue(grid.getSquares()[3][0].isMine());
        assertTrue(grid.getSquares()[3][3].isMine());
    }

    @Test
    public void testAdjacentMinesOfGridWhenAMineIsPlaced() {
        Random random = new Random();
        int gridSize = 9;
        Grid grid = new Grid(gridSize, 1, random);

        Random mockRandom = Mockito.mock(Random.class);
        Mockito.when(mockRandom.nextInt(gridSize))
                .thenReturn(4,4);

        grid.initializeGrid();
        grid.placeMines(mockRandom);
        grid.setAdjacentMinesOfGrid();

        // all the squares around the placed mine should have adjacent value of 1

        for (int i = 3; i <= 5; i++) {
            for (int j = 3; j <= 5; j++) {
                if (i != 4 && j != 4) {
                    Square sq = grid.getSquares()[i][j];
                    assertEquals(1, sq.getAdjacentMines());
                }
            }
        }
    }

    @Test
    public void testSetAdjacentMinesOfGridWhenMinesAreAtBottomLeftAndRight() {
        Random random = new Random();
        int gridSize = 9;
        Grid grid = new Grid(gridSize, 2, random);

        Random mockRandom = Mockito.mock(Random.class);
        Mockito.when(mockRandom.nextInt(gridSize))
                .thenReturn(8,0, 8, 8);

        grid.initializeGrid();
        grid.placeMines(mockRandom);
        grid.setAdjacentMinesOfGrid();

        // there should be 6 squares with adjacent mines value 1
        int totalAdjacentMinesCount = 0;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
               Square sq = grid.getSquares()[i][j];
               totalAdjacentMinesCount += sq.getAdjacentMines();
            }
        }

        assertEquals(6, totalAdjacentMinesCount);
    }

    @Test
    public void testSetAdjacentMinesOfGridWhenMinesAreAdjacent() {
        Random random = new Random();
        int gridSize = 9;
        Grid grid = new Grid(gridSize, 2, random);

        Random mockRandom = Mockito.mock(Random.class);
        Mockito.when(mockRandom.nextInt(gridSize))
                .thenReturn(8,0, 8, 1);

        grid.initializeGrid();
        grid.placeMines(mockRandom);
        grid.setAdjacentMinesOfGrid();

        // there should be 2 squares with adjacent mines value 1
        // there should be 2 squares with adjacent mines value 2

        int totalAdjacentMinesCount = 0;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
               Square sq = grid.getSquares()[i][j];
               totalAdjacentMinesCount += sq.getAdjacentMines();
            }
        }

        assertEquals(6, totalAdjacentMinesCount);
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
