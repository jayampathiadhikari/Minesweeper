package com.assignment.minesweeper.model;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SquareTest {
    @Test
    public void testSquareInitialization() {
        Square square = new Square();
        assertFalse(square.isMine());
        assertFalse(square.isUncovered());
        assertEquals(0, square.getAdjacentMines());
    }

    @Test
    public void test() {
        int size = 20000;
        Square[][] squares = new Square[size][size];
//        for (int row = 0; row < size; row++) {
//            for (int col = 0; col < size; col++) {
//                squares[row][col] = new Square();
//            }
        }
//        Arrays.stream(squares).parallel().forEach(s -> Arrays.parallelSetAll(s, i -> new Square()));


}
