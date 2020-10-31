package com.scrabble.client.model;

import javafx.util.Pair;

import java.util.LinkedList;

public class PlayInfo {
    private LinkedList<CellContent> cellContents;
    private Direction direction;
    private Pair<Integer, Integer> startingCell;
    private Boolean pass;

    public PlayInfo(LinkedList<CellContent> cellContents, Direction direction,
                    Pair<Integer, Integer> startingCell) {
        this.cellContents = cellContents;
        this.direction = direction;
        this.startingCell = startingCell;
        pass = false;
    }

    public PlayInfo(boolean pass) {
        this.pass = pass;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public LinkedList<CellContent> getCellContents() {
        return cellContents;
    }

    public void setCellContents(LinkedList<CellContent> cellContents) {
        this.cellContents = cellContents;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Pair<Integer, Integer> getStartingCell() {
        return startingCell;
    }

    public void setStartingCell(Pair<Integer, Integer> startingCell) {
        this.startingCell = startingCell;
    }
}
