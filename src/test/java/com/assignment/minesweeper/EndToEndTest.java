package com.assignment.minesweeper;

import com.assignment.minesweeper.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EndToEndTest {
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
    public void testE2ERunGameWin() {
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
    public void testE2ERunGameLoss() {
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
    public void testE2ERunGameWinRestartLoss() {
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
    public void testE2ERunGameLossRestartThenWin() {
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
