package com.scrabble.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlayerHandler implements Runnable {
    private final Socket socket;
    private ObjectInputStream ois = null;
    private ObjectOutputStream ous = null;
    private final AtomicBoolean adminSelected;
    private final PlayerInfo playerInfo;
    private Boolean isAdmin = false;
    private HashMap<Socket, PlayerInfo> socketPlayerHashMap;

    public PlayerHandler(PlayerInfo playerInfo, Socket socket,
                         AtomicBoolean atomicBoolean,
                         HashMap<Socket, PlayerInfo> playerHashMap) {
        this.socket = socket;
        this.adminSelected = atomicBoolean;
        this.playerInfo = playerInfo;
        socketPlayerHashMap = playerHashMap;
    }


    @Override
    public void run() {
        // start gathering players then start the game
        prepareGame();
        // handle game logic
    }


    public void prepareGame() {
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            ous = new ObjectOutputStream(socket.getOutputStream());
            String name = (String) ois.readObject();
            playerInfo.setName(name);
            handleUpdateNewPlayers();
            if (!adminSelected.get()) {
                synchronized (adminSelected) {
                    adminSelected.set(true);
                    isAdmin = true;
                }
                playerInfo.setAdmin(true);
                while (true) {
                    String startCommand = (String) ois.readObject();
                    if (startCommand.toLowerCase().equals("start")) {
                        PlayerInfo.setStartGame(true);
                        return;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handleUpdateNewPlayers() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    ous.writeObject(socketPlayerHashMap);
                    if (PlayerInfo.getGameStarted()) {
                        ous.writeObject("Game Started!");
                        timer.purge();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 1, 800);
    }
}
