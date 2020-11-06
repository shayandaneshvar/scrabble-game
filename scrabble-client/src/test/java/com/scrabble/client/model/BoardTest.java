package com.scrabble.client.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void insertCell() {
        Board board = new Board();

        board.insertCell(2, 2, CellContent.E);

        CellContent c = board.getCell(2, 2);

        Assertions.assertEquals(c, CellContent.E);

        board.insertCell(2, 2, CellContent.D);

        Assertions.assertEquals(c, CellContent.E);
    }
}
