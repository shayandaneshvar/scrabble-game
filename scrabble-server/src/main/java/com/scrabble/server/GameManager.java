package com.scrabble.server;

public class GameManager implements Runnable {
    private CircularQueue<Player> players;

    public GameManager(CircularQueue<Player> players) {
        this.players = players;
        players.init();
    }

    @Override
    public void run() {
        while (true){
            Player player = players.pop();
//            player.
            // TODO: 11/6/2020 game loop
        }
    }
}
