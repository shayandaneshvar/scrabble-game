package com.scrabble.client.model;

import com.scrabble.client.view.Observer;
import com.scrabble.server.dto.PlayerInfo;

import java.util.ArrayList;
import java.util.List;

public class NetworkEnabledGame extends Game<NetworkEnabledGame> {
    private List<Observer<NetworkEnabledGame>> observers = new ArrayList<>();
    private Player player;
    private final List<PlayerInfo> otherPlayers = new ArrayList<>();

    public List<Observer<NetworkEnabledGame>> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer<NetworkEnabledGame>> observers) {
        this.observers = observers;
    }

    public List<PlayerInfo> getOtherPlayers() {
        return otherPlayers;
    }


    public NetworkEnabledGame(Board board, Player player) {
        super(board);
        this.player = player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public Board getBoard() {
        return super.getBoard().clone();
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
