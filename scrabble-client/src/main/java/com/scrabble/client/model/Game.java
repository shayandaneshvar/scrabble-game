package com.scrabble.client.model;

public abstract class Game<T extends Game<T>> implements Observable<T> {
    protected Board board;

    protected Board getBoard() {
        return board;
    }

    protected void setBoard(Board board) {
        this.board = board;
    }


}
