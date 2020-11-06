package com.scrabble.client.model;

public class Board implements Cloneable {
    private Cell[][] cells = new Cell[14][14];

    {
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    @Override
    public Board clone() {
        Board board = null;
        try {
            board = (Board) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        board.cells = cells.clone();
        return board;
    }

    public Board() {
    }

    public boolean insertCell(int x, int y, CellContent content) {
        if (cells[x][y].getContent() == CellContent.EMPTY) {
            cells[x][y].setContent(content);
            return true;
        }
        return false;
    }

    public CellContent getCell(int x, int y) {
        return cells[x][y].getContent();
    }
}
