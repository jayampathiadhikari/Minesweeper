package com.assignment.minesweeper.model;

import java.util.Random;


public class GameGrid implements Grid {
    private int size;
    private int mineCount;
    private final Square[][] squares;
    private boolean gameOver = false;
    private final int[] minePosRows;
    private final int[] minePosCols;
    private Random random;
    private int uncoveredCount;
    private final int totalSquares;

    public GameGrid(int size, int mineCount, Random random) {
        this.size = size;
        this.mineCount = mineCount;
        this.squares = new GameSquare[size][size];
        this.minePosRows = new int[mineCount];
        this.minePosCols = new int[mineCount];
        this.random = random;
        this.totalSquares = size * size;
    }

    // call this method after object creation
    @Override
    public void loadGrid() {
        initializeGrid();
        placeMines(random);
        setAdjacentMinesOfGrid();
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public boolean isWin() {
        return uncoveredCount + mineCount == totalSquares;
    }

    @Override
    public Square[][] getSquares() {
        return squares;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getAdjacentMinesCountOfSquare(int row, int col) {
        return squares[row][col].getAdjacentMines();
    }

    @Override
    public boolean uncoverSquare(int row, int col) {
        // if mine, game over
        if (squares[row][col].isMine()) {
            gameOver = true;
            return false;
        } else {
            uncover(row, col);
            return true;
        }
    }

    // initialize grid with squares
    void initializeGrid() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                squares[row][col] = new GameSquare();
            }
        }
    }

    // place mines in random squares
    void placeMines(Random random) {
        int minesPlaced = 0;
        while (minesPlaced < mineCount) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            Square sq = squares[row][col];
            if (!sq.isMine()) {
                sq.setMine(true);
                sq.setAdjacentMines(0);
                //save mine positions for easy adjacent mine counting
                minePosRows[minesPlaced] = row;
                minePosCols[minesPlaced] = col;
                minesPlaced++;
            }
        }
    }

    // set the mines count of adjacent squares
    void setAdjacentMinesOfGrid() {
        for (int i = 0; i < mineCount; i++) {
            setAdjacentMinesCount(minePosRows[i], minePosCols[i]);
        }
    }

    void setAdjacentMinesCount(int mineRow, int mineCol) {
        for (int i = Math.max(mineRow - 1, 0); i <= Math.min(mineRow + 1, size - 1); i++) {
            for (int j = Math.max(mineCol - 1, 0); j <= Math.min(mineCol + 1, size - 1); j++) {
                Square sq = squares[i][j];
                if (!sq.isMine()) {
                    sq.setAdjacentMines(sq.getAdjacentMines() + 1);
                }
            }
        }
    }

    void uncover(int row, int col) {
        // if already uncovered, return
        if (squares[row][col].isUncovered()) {
            return;
        }
        squares[row][col].setUncovered(true);
        uncoveredCount++;
        // if no adjacentMines, search the neighbours
        if (squares[row][col].getAdjacentMines() == 0) {
            for (int i = Math.max(row - 1, 0); i <= Math.min(row + 1, size - 1); i++) {
                for (int j = Math.max(col - 1, 0); j <= Math.min(col + 1, size - 1); j++) {
                    uncover(i, j);
                }
            }
        }
    }
}
