package com.scrabble.client.model;

public class Board {
    private Cell[][] cells = new Cell[14][14];

    {
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                cells[i][j] = new Cell();
            }
        }
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
