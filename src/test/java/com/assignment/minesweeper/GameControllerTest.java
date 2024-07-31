package com.assignment.minesweeper;

import java.util.Random;

import com.assignment.minesweeper.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class GameControllerTest {
    @Mock
    View view;

    @Mock
    UserInputScanner userInputScanner;

    Random random;

    GameController gameController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInitializeGame() {
        random = new Random();
        gameController = new GameController(view, userInputScanner, random);
        when(userInputScanner.getUserInputAsString()).thenReturn("4", "5");
        gameController.initializeGame();

        verify(view).displayWelcome();
        verify(view).displayMessage("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
        verify(view).displayMessage("Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
    }

    @Test
    public void testIsRestartWhenYes() {
        when(userInputScanner.getUserInputAsString()).thenReturn("a","1", "y");
        gameController = new GameController(view, userInputScanner, random);
        boolean playAgain = gameController.isRestart();

        verify(view, times(2)).displayMessage("Incorrect input.");
        assertEquals(true, playAgain);
    }

    @Test
    public void testIsRestartWhenNo() {
        when(userInputScanner.getUserInputAsString()).thenReturn("a","1", "n");
        gameController = new GameController(view, userInputScanner, random);
        boolean playAgain = gameController.isRestart();

        verify(view, times(2)).displayMessage("Incorrect input.");
        verify(view).displayMessage("Quitting Game...");
        assertEquals(false, playAgain);
    }

    @Test
    public void testGetValidGridSize() {
        when(userInputScanner.getUserInputAsString()).thenReturn("a","2", "11", "abc", "4");
        gameController = new GameController(view, userInputScanner, random);
        int gridSize = gameController.getValidGridSize();

        verify(view).displayMessage("Minimum size of grid is 3.");
        verify(view).displayMessage("Maximum size of grid is 10.");
        verify(view, times(2)).displayMessage("Incorrect input.");
        assertEquals(4, gridSize);
    }

    @Test
    public void testGetValidMinesCount() {
        when(userInputScanner.getUserInputAsString()).thenReturn("a","0", "15", "2");
        gameController = new GameController(view, userInputScanner, random);
        int minesCount = gameController.getValidMinesCount(4);

        verify(view).displayMessage("Incorrect input.");
        verify(view).displayMessage("There must be at least 1 mine.");
        verify(view).displayMessage("Maximum number is 35% of total squares.");
        assertEquals(2, minesCount);
    }

    @Test
    public void testGetValidSquareToReveal() {

        Random mockRandom = Mockito.mock(Random.class);
        when(mockRandom.nextInt(2))
                .thenReturn(0, 0);
        when(userInputScanner.getUserInputAsString()).thenReturn("4", "1", "A", "A9", "A2");

        gameController = new GameController(view, userInputScanner, mockRandom);
        gameController.initializeGame();

        int[] squarePos = gameController.getValidSquareToReveal();
        verify(view, times(2)).displayMessage("Incorrect input.");
        assertArrayEquals(new int[]{0, 1}, squarePos);
    }

    @Test
    public void testRunGameWin() {
        Random mockRandom = Mockito.mock(Random.class);
        when(mockRandom.nextInt(2))
                .thenReturn(0, 0);
        gameController = new GameController(view, userInputScanner, mockRandom);

        when(userInputScanner.getUserInputAsString()).thenReturn("3", "1", "C3", "B1", "B2", "A2", "n");

        gameController.initializeGame();
        gameController.runGame();

        verify(view).displayMessage("Congratulations, you have won the game!");
    }

    @Test
    public void testRunGameLoss() {
        Random mockRandom = Mockito.mock(Random.class);
        when(mockRandom.nextInt(2))
                .thenReturn(0, 0);

        gameController = new GameController(view, userInputScanner, mockRandom);

        when(userInputScanner.getUserInputAsString()).thenReturn("4", "1", "A1", "n");

        gameController.initializeGame();
        gameController.runGame();

        verify(view).displayMessage("Oh no, you detonated a mine! Game over.");
    }

    @Test
    public void testRunGameWinRestartLoss() {
        Random mockRandom = Mockito.mock(Random.class);
        when(mockRandom.nextInt(2))
                .thenReturn(0, 0);
        gameController = new GameController(view, userInputScanner, mockRandom);

        when(userInputScanner.getUserInputAsString()).thenReturn("3", "1", "C3", "B1", "B2", "A2", "y", "3", "1", "A1", "n");

        gameController.initializeGame();
        gameController.runGame();

        verify(view).displayMessage("Congratulations, you have won the game!");
        verify(view).displayMessage("Oh no, you detonated a mine! Game over.");
    }

    @Test
    public void testRunGameLossRestartThenWin() {
        Random mockRandom = Mockito.mock(Random.class);
        when(mockRandom.nextInt(2))
                .thenReturn(0, 0);

        gameController = new GameController(view, userInputScanner, mockRandom);

        when(userInputScanner.getUserInputAsString()).thenReturn("3", "1", "A1", "y", "3", "1", "C3", "B1", "B2", "A2", "n");

        gameController.initializeGame();
        gameController.runGame();

        verify(view).displayMessage("Oh no, you detonated a mine! Game over.");
        verify(view).displayMessage("Congratulations, you have won the game!");
    }

}
