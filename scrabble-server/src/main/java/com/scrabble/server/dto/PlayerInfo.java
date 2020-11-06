package com.scrabble.server.dto;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Objects;

public class PlayerInfo implements Serializable {
    private String name;
    private Boolean isAdmin = false;
    private static volatile Boolean gameStarted = false;
    private InetAddress address;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerInfo)) return false;
        PlayerInfo that = (PlayerInfo) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress());
    }

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

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "name='" + name + '\'' +
                ", isAdmin=" + isAdmin +
                ", address=" + address +
                '}';
    }

    public static Boolean getGameStarted() {
        return gameStarted;
    }
}
