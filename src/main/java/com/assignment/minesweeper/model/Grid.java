package com.assignment.minesweeper.model;

import java.util.Random;


public class Grid {
    private final int size;
    private final int mineCount;
    private final Square[][] squares;
    private boolean gameOver = false;
    private final int[] minePosRows;
    private final int[] minePosCols;
    private final Random random;

    public Grid(int size, int mineCount, Random random) {
        this.size = size;
        this.mineCount = mineCount;
        this.squares = new Square[size][size];
        this.minePosRows = new int[mineCount];
        this.minePosCols = new int[mineCount];
        this.random = random;
    }

    // call this method after object creation
    void loadGrid () {
        initializeGrid();
        placeMines(random);
        setAdjacentMinesOfGrid();
    }

    // initialize grid with squares
    void initializeGrid() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                squares[row][col] = new Square();
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
    };

    void setAdjacentMinesCount(int mineRow, int mineCol) {
        for (int i = Math.max(mineRow - 1,0); i <= Math.min(mineRow + 1, size-1); i++) {
            for (int j = Math.max(mineCol - 1, 0); j <= Math.min(mineCol + 1, size-1); j++) {
                Square sq = squares[i][j];
                if (!sq.isMine()) {
                    sq.setAdjacentMines(sq.getAdjacentMines()+1);
                }
            }
        }
    }

    private void calculateAdjacency() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (!squares[row][col].isMine()) {
                    int adjacentMines = countAdjacentMines(row, col);
                    squares[row][col].setAdjacentMines(adjacentMines);
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int mines = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < size && j >= 0 && j < size && squares[i][j].isMine()) {
                    mines++;
                }
            }
        }
        return mines;
    }

    public boolean uncoverSquare(int row, int col) {
        if (squares[row][col].isMine()) {
            gameOver = true;
            return false;
        } else {
            uncover(row, col);
            return true;
        }
    }

    private void uncover(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size || squares[row][col].isUncovered()) {
            return;
        }
        squares[row][col].setUncovered(true);
        if (squares[row][col].getAdjacentMines() == 0) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    uncover(i, j);
                }
            }
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isWin() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (!squares[row][col].isMine() && !squares[row][col].isUncovered()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Square[][] getSquares() {
        return squares;
    }


}
