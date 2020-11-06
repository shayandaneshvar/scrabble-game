package com.scrabble.server.dto;

import java.io.Serializable;
import java.net.InetAddress;

public class PlayerInfo implements Serializable {
    private String name;
    private Boolean isAdmin = false;
    private static volatile Boolean gameStarted = false;
    private InetAddress address;

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

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
