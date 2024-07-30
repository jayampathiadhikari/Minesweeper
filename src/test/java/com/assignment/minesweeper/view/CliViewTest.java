package com.assignment.minesweeper.view;

import com.assignment.minesweeper.model.Grid;
import org.junit.jupiter.api.Test;
import java.util.Random;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CliViewTest {
    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    final PrintStream originalOut = System.out;
    final Random random = new Random();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }


    @Test
    public void testDisplayGridForSize3() {
        int gridSize = 3;
        Grid grid = new Grid(gridSize, 2, random);
        grid.loadGrid();

        CliView cliView = new CliView();
        cliView.displayGrid(grid);

        String expectedOutput =
                "  1 2 3\n" +
                "A _ _ _\n" +
                "B _ _ _\n" +
                "C _ _ _\n";

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testDisplayGridForSize10() {
        int gridSize = 10;
        Grid grid = new Grid(gridSize, 2, random);
        grid.loadGrid();

        CliView cliView = new CliView();
        cliView.displayGrid(grid);

        String expectedOutput =
                "  1 2 3 4 5 6 7 8 9 10\n" +
                "A _ _ _ _ _ _ _ _ _ _\n" +
                "B _ _ _ _ _ _ _ _ _ _\n" +
                "C _ _ _ _ _ _ _ _ _ _\n" +
                "D _ _ _ _ _ _ _ _ _ _\n" +
                "E _ _ _ _ _ _ _ _ _ _\n" +
                "F _ _ _ _ _ _ _ _ _ _\n" +
                "G _ _ _ _ _ _ _ _ _ _\n" +
                "H _ _ _ _ _ _ _ _ _ _\n" +
                "I _ _ _ _ _ _ _ _ _ _\n" +
                "J _ _ _ _ _ _ _ _ _ _\n";

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testDisplayGridWhenSquaresAreUncovered() {
        int gridSize = 10;
        Random mockRandom = Mockito.mock(Random.class);
        Mockito.when(mockRandom.nextInt(gridSize))
                .thenReturn(0,0, 8, 4, 6, 3);

        Grid grid = new Grid(gridSize, 3, mockRandom);
        grid.loadGrid();

        // uncover some squares
        grid.getSquares()[0][1].setUncovered(true);
        grid.getSquares()[8][3].setUncovered(true);
        grid.getSquares()[6][2].setUncovered(true);
        grid.getSquares()[6][4].setUncovered(true);

        CliView cliView = new CliView();
        cliView.displayGrid(grid);

        String expectedOutput =
                     "  1 2 3 4 5 6 7 8 9 10\n" +
                    "A _ 1 _ _ _ _ _ _ _ _\n" +
                    "B _ _ _ _ _ _ _ _ _ _\n" +
                    "C _ _ _ _ _ _ _ _ _ _\n" +
                    "D _ _ _ _ _ _ _ _ _ _\n" +
                    "E _ _ _ _ _ _ _ _ _ _\n" +
                    "F _ _ _ _ _ _ _ _ _ _\n" +
                    "G _ _ 1 _ 1 _ _ _ _ _\n" +
                    "H _ _ _ _ _ _ _ _ _ _\n" +
                    "I _ _ _ 1 _ _ _ _ _ _\n" +
                    "J _ _ _ _ _ _ _ _ _ _\n"  ;

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

}
