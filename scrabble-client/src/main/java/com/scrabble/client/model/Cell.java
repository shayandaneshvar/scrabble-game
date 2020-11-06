package com.scrabble.client.model;

public class Cell implements Cloneable {
    private CellContent content;

    public Cell(CellContent character){
        content = character;
    }

    public Cell(){
        content = CellContent.EMPTY;
    }

    @Override
    public Cell clone() throws CloneNotSupportedException {
        return (Cell) super.clone();
    }

    public CellContent getContent() {
        return content;
    }

    public void setContent(CellContent content) {
        this.content = content;
    }
}
