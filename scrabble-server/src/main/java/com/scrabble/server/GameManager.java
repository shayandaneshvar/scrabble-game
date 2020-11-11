package com.scrabble.server;

import java.util.List;
import java.util.Set;

public class GameManager implements Runnable {
    private CircularQueue<Player> players;

    public GameManager(CircularQueue<Player> players) {
        this.players = players;
        players.init();
    }

    @Override
    public void run() {
        distributePieces();
        while (true) {
            Player player = players.pop();
//            player.
            // TODO: 11/6/2020 game loop
        }
    }

    private void distributePieces() {
        Set<List<Character>> wordsLists = WordUtil.DICTIONARY
                .generatePlayerPieces(players.size());
        if (wordsLists == null) {
            distributePieces();
            return;
        }
        wordsLists.forEach(z -> players.pop().sendObject(z));
    }


}
