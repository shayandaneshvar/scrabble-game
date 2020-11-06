package com.scrabble.client.controller;

import com.scrabble.client.model.Board;
import com.scrabble.client.model.NetworkEnabledGame;
import com.scrabble.client.model.Player;
import com.scrabble.client.view.MultiPlayerGameView;
import com.scrabble.server.dto.PlayerInfo;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class NetworkEnabledGameController {
    private NetworkEnabledGame game;
    private MultiPlayerGameView gameView;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private String host;

    public NetworkEnabledGameController(Player player, Stage primaryStage) {
        game = new NetworkEnabledGame(new Board(), player);
        gameView = new MultiPlayerGameView(primaryStage);
        gameView.setGameStartCommand(this::startGame);
        game.addObserver(gameView);
    }


    public void init(String host) {
        game.updateObservers();
        this.host = host;
        new Thread(this::startCommunication).start();
    }

    public void startGame(Boolean isAdmin) {
        try {
            oos.reset();
            oos.writeObject("start");
            // TODO: 11/6/2020 game loop
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startCommunication() {
        System.out.println("Connecting to " + host);
        try {
            socket = new Socket(host, 43210);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            oos.writeObject(game.getPlayer().getName());
            while (true) {
                Thread.sleep(800);
                Object object = ois.readObject();
                if (object instanceof String) {
                    String command = (String) object;
                    if (command.toLowerCase().contains("start")) {
                        startGame(false);
                        return;
                    } else {
                        continue;
                    }
                }
                List<PlayerInfo> playerInfoList = (List<PlayerInfo>) object;
                synchronized (game.getOtherPlayers()) {
                    game.getOtherPlayers().clear();
                    game.getOtherPlayers().addAll(playerInfoList);
                }
                Platform.runLater(() -> game.updateObservers());
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
