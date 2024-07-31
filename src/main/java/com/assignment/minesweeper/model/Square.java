package com.assignment.minesweeper.model;

public interface Square {
    boolean isMine();

    void setMine(boolean isMine);

    boolean isUncovered();

    void setUncovered(boolean isUncovered);

    int getAdjacentMines();

    void setAdjacentMines(int adjacentMines);
}
