package com.scrabble.server;

import com.scrabble.server.dto.PlayerInfo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Player {
    private PlayerInfo playerInfo;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public Player(PlayerInfo playerInfo, ObjectInputStream ois, ObjectOutputStream oos) {
        this.playerInfo = playerInfo;
        this.ois = ois;
        this.oos = oos;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }
}
