package com.scrabble.server;

public class GameManager implements Runnable {
    private CircularQueue<Player> players;

    public GameManager(CircularQueue<Player> players) {
        this.players = players;
        players.init();
    }

    @Override
    public void run() {

    }
}
