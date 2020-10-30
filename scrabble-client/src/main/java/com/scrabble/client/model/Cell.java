package com.scrabble.client.model;

public class Cell {
    private CellContent content;

    public Cell(CellContent character){
        content = character;
    }

    public Cell(){
        content = CellContent.EMPTY;
    }


    public CellContent getContent() {
        return content;
    }

    public void setContent(CellContent content) {
        this.content = content;
    }
}
