package com.scrabble.client.controller;

import com.scrabble.client.model.Board;
import com.scrabble.client.model.NetworkEnabledGame;
import com.scrabble.client.model.Player;
import com.scrabble.client.view.MultiPlayerGameView;
import javafx.stage.Stage;

public class NetworkEnabledGameController {
    private NetworkEnabledGame game;
    private MultiPlayerGameView gameView;

    public NetworkEnabledGameController(Player player, Stage primaryStage) {
        game = new NetworkEnabledGame(new Board(), player);
        gameView = new MultiPlayerGameView(primaryStage);
        game.addObserver(gameView);
    }


    public void init() {
        game.updateObservers();
    }
}
