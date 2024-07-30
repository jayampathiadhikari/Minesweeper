//package com.assignment.minesweeper;
//
//
///**
// * Game manager is used for the lifecycle management of the game
// *
// * Initializes the game board and places mines.
// * Manages the game state (e.g., ongoing, won, lost).
// * Handles transitions between different states or levels.
// * Manages game resources and configurations.
// */
//
//public class GameManager {
//    private Grid gameBoard;
//    private GameController gameController;
//    private GameView gameView;
//
//    public GameManager(int size, int numMines) {
//        this.gameBoard = new Grid(size, numMines);
//        this.gameView = new GameView();
//        this.gameController = new GameController(gameBoard, gameView);
//    }
//
//    public void startGame() {
//        gameBoard.initialize();
//        gameView.displayBoard(gameBoard);
//
//        boolean isGameOver = false;
//        while (!isGameOver) {
//            gameController.promptUser();
//            isGameOver = gameBoard.isGameOver();
//        }
//
//        if (gameBoard.isWin()) {
//            gameView.displayWinMessage();
//        } else {
//            gameView.displayLoseMessage();
//        }
//    }
//
//    public void resetGame(int size, int numMines) {
//        this.gameBoard = new Grid(size, numMines);
//        this.gameController = new GameController(gameBoard, gameView);
//        startGame();
//    }
//}
//
//
