package com.scrabble.client.model;

import com.scrabble.client.view.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Game implements Observable {
    private Board board;
    private List<Observer<Game>> observers = new ArrayList<>();


    @Override
    public void addObserver(Observer observer) {
        observers.add((Observer<Game>) observer);
    }

    @Override
    public void updateObservers() {
        observers.forEach(z -> z.update(this));
    }
}
