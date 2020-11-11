package com.scrabble.client.model;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    public List<CellContent> characters = new ArrayList<>();

    public void setCharacters(List<Character> chars) {
        chars.stream()
                .map(z -> CellContent.valueOf(String.valueOf(z)))
                .forEach(characters::add);
    }

    public List<CellContent> getCharacters() {
        return characters;
    }

    @Override
    public PlayInfo play() {
        return null;
    }
}
