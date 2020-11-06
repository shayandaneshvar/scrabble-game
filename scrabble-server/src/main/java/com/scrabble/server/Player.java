package com.scrabble.server;

import com.scrabble.server.dto.PlayerInfo;

import java.io.IOException;
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

    public void sendObject(Object object) {
        try {
            oos.reset();
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object readObject() {
        try {
            while (ois.available() <= 1) {
                Thread.sleep(50);
            }
            return ois.readObject();
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
