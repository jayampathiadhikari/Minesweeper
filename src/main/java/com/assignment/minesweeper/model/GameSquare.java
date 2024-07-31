package com.assignment.minesweeper.model;

/**
 * POJO for keeping square information.
 * Therefore, no need of unitTests
 */

public class GameSquare implements Square {
    private boolean isMine;
    private boolean isUncovered;
    private int adjacentMines;

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isUncovered() {
        return isUncovered;
    }

    public void setUncovered(boolean isUncovered) {
        this.isUncovered = isUncovered;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }
}
