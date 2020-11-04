package com.scrabble.server;

public class PlayerInfo {
    private String name;
    private Boolean isAdmin = false;
    private static volatile Boolean gameStarted = false;

    public String getName() {
        return name;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerInfo(String name) {
        this.name = name;
    }

    public static void setStartGame(boolean b) {
        gameStarted = b;
    }

    public static Boolean getGameStarted() {
        return gameStarted;
    }
}
