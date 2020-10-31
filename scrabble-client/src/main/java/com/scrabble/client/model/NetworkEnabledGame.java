package com.scrabble.client.model;

import com.scrabble.client.view.Observer;

import java.util.ArrayList;
import java.util.List;

public class NetworkEnabledGame extends Game<NetworkEnabledGame> {
    private List<Observer<NetworkEnabledGame>> observers = new ArrayList<>();
    private Player player;

    public NetworkEnabledGame(Board board, Player player) {
        super(board);
        this.player = player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void addObserver(Observer<NetworkEnabledGame> observer) {
        observers.add(observer);
    }

    @Override
    public void updateObservers() {
        observers.forEach(z -> z.update(this));
    }
}
