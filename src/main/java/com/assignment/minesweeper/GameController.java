//package com.assignment.minesweeper;
//
//import com.assignment.minesweeper.model.Grid;
//import com.assignment.minesweeper.view.CliView;
//
//
///**
// * GameController handles user inputs,
// * control the flow of the game based on user actions,
// * and interact with the view and grid.
// */
//
//public class GameController {
//    private final Grid grid;
//    private final CliView view;
//
//    public GameController(Grid grid, CliView view) {
//        this.grid = grid;
//        this.view = view;
//    }
//
//    public void startGame() {
//        while (!grid.isGameOver() && !grid.isWin()) {
//            view.displayGrid(grid);
//            int[] input = view.getUserInput();
//            boolean safeMove = grid.uncoverSquare(input[0], input[1]);
//            if (!safeMove) {
//                view.displayGrid(grid);
//                view.showGameOver();
//                return;
//            }
//        }
//
//        if (grid.isWin()) {
//            view.displayGrid(grid);
//            view.showWin();
//        }
//    }
//}
//
