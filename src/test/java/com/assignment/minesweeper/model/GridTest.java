package com.assignment.minesweeper.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

public class GridTest {

    @Test
    public void testGridShouldProperlyBePopulated() {
        Random mockRandom = Mockito.mock(Random.class);
        Mockito.when(mockRandom.nextInt(4))
                .thenReturn(3,0, 3, 3);

        int x = mockRandom.nextInt();
        int y = mockRandom.nextInt();
        Grid grid = new Grid(4,2, mockRandom );
        Square square = new Square();
        assertFalse(square.isMine());
        assertFalse(square.isUncovered());
        assertEquals(0, square.getAdjacentMines());
    }
}
