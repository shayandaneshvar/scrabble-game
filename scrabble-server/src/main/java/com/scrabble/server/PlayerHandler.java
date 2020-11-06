package com.scrabble.server;

import com.scrabble.server.dto.PlayerInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

public class PlayerHandler implements Runnable {
    private final Socket socket;
    private ObjectInputStream ois = null;
    private ObjectOutputStream ous = null;
    private final AtomicBoolean adminSelected;
    private final PlayerInfo playerInfo;
    private Boolean isAdmin = false;
    private volatile List<PlayerInfo> playerInfos;
    private BiConsumer<SocketException, PlayerInfo> disconnectionCommand;

    public PlayerHandler(PlayerInfo playerInfo, Socket socket,
                         AtomicBoolean atomicBoolean,
                         List<PlayerInfo> playerInfos) {
        this.socket = socket;
        this.adminSelected = atomicBoolean;
        this.playerInfo = playerInfo;
        this.playerInfos = playerInfos;
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
                while (ois.available() <= 1) {
                    String startCommand = (String) ois.readObject();
                    if (startCommand.toLowerCase().equals("start")) {
                        PlayerInfo.setStartGame(true);
                        return;
                    }
                }
            }
        } catch (SocketException s) {
            disconnectionCommand.accept(s, playerInfo);
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
                    ous.writeObject(playerInfos);
                    if (PlayerInfo.getGameStarted()) {
                        ous.writeObject("Game Started!");
                        timer.purge();
                    }
                } catch (SocketException e) {
                    disconnectionCommand.accept(e, playerInfo);
                    timer.purge();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 1, 800);
    }

    public void setDisconnectionCommand(BiConsumer<SocketException, PlayerInfo> consumer) {
        this.disconnectionCommand = consumer;
    }
}
