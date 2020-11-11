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
            player.sendObject("turn");
            String word = (String) player.readObject();
            if (WordUtil.DICTIONARY.checkWord(word)) {
                player.sendObject("accepted");
                String str = (String) player.readObject(); // "x,y,(h/v)"
                players.toList().stream().filter(z -> !z.equals(player))
                        .forEach(z -> z.sendObject(word + "," + str));
            } else {
                player.sendObject("unaccepted");
            }
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
