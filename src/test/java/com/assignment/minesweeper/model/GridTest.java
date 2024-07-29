package com.assignment.minesweeper.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class GridTest {

    @Test
    public void testGridShouldProperlyBePopulated() {
        Grid grid = new Grid(4,3 );
        Square square = new Square();
        assertFalse(square.isMine());
        assertFalse(square.isUncovered());
        assertEquals(0, square.getAdjacentMines());
    }
}
