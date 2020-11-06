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
    private volatile PlayerInfo playerInfo;
    private final AtomicBoolean adminSelected;
    private BiConsumer<SocketException, PlayerInfo> disconnectionCommand;
    private Runnable gameStart, updateCommand;

    public PlayerHandler(PlayerInfo playerInfo, Socket socket,
                         AtomicBoolean atomicBoolean) {
        this.socket = socket;
        this.adminSelected = atomicBoolean;
        this.playerInfo = playerInfo;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void update(List<PlayerInfo> playerInfos) {
        while (playerInfo.getName().equals("Undefined") || ous == null) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(playerInfos + " T: " + Thread.currentThread().getName() + " from:" + playerInfo.getName());
        try {
            ous.reset();
            ous.writeObject(playerInfos);
            if (PlayerInfo.getGameStarted()) {
                ous.writeObject("Game Started!");
            }
        } catch (SocketException e) {
            disconnectionCommand.accept(e, playerInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            ous = new ObjectOutputStream(socket.getOutputStream());
            String name = (String) ois.readObject();
            playerInfo.setName(name);
            handleAdminSelection();
        } catch (SocketException s) {
            disconnectionCommand.accept(s, playerInfo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handleAdminSelection() {
        Timer adminTimer = new Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                synchronized (adminSelected) {
                    if (!adminSelected.get()) {
                        adminSelected.set(true);
                        playerInfo.setAdmin(true);
                        updateCommand.run();
                    } else {
                        return;
                    }
                }
                if (playerInfo.getAdmin()) {
                    try {
                        if (!(ois.available() <= 1)) {
                            return;
                        }
                        String startCommand = (String) ois.readObject();
                        if (startCommand.toLowerCase().equals("start")) {
                            PlayerInfo.setStartGame(true);
                            gameStart.run();
                            adminTimer.purge();
                        }
                    } catch (SocketException e) {
                        disconnectionCommand.accept(e, playerInfo);
                        adminTimer.purge();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (PlayerInfo.getGameStarted()) {
                    adminTimer.purge();
                }
            }
        };
        adminTimer.schedule(task1, 1, 800);
    }

    public void setDisconnectionCommand(BiConsumer<SocketException, PlayerInfo> consumer) {
        this.disconnectionCommand = consumer;
    }

    public void setStartGameCommand(Runnable startGame) {
        gameStart = startGame;
    }

    public void setUpdateCommand(Runnable updateEachPlayer) {
        this.updateCommand = updateEachPlayer;
    }
}
