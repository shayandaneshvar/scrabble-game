package com.scrabble.client.model;

public enum Direction {
    VERTICAL, HORIZONTAL;

    public static Direction getDirection(String string) {
        if (string.toLowerCase().contains("v")) {
            return VERTICAL;
        } else if (string.toLowerCase().contains("h")) {
            return HORIZONTAL;
        }
        return null;
    }
}
