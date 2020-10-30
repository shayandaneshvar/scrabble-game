package com.scrabble.client.model;

public class Cell {
    private Character content;

    public Cell(Character character){
        content = character;
    }

    public Cell(){
        content = ' ';
    }


    public Character getContent() {
        return content;
    }

    public void setContent(Character content) {
        this.content = content;
    }
}
