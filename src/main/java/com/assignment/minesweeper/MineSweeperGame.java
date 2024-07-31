package com.assignment.minesweeper;

import com.assignment.minesweeper.view.CliView;
import com.assignment.minesweeper.view.View;

import java.util.Random;

public class MineSweeperGame {
    public static void main(String[] args) {
        try (UserInputScanner userInputScanner = new UserInputScanner()) {
            Random random = new Random();
            View cliView = new CliView();

            GameController gameController = new GameController(cliView, userInputScanner, random);
            gameController.initializeGame();
            gameController.runGame();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
