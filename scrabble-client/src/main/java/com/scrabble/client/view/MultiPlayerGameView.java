package com.scrabble.client.view;

import com.scrabble.client.model.NetworkEnabledGame;

public class MultiPlayerGameView implements GameView<NetworkEnabledGame>,
        Observer<NetworkEnabledGame> {

    @Override
    public void drawGameBoard(NetworkEnabledGame game) {

    }


    @Override
    public void update(NetworkEnabledGame game) {
        drawGameBoard(game);
    }
}
