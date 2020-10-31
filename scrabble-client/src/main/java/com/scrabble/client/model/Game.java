package com.scrabble.client.model;

import com.scrabble.client.view.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Game implements Observable<Game> {
    private Board board;
    private List<Observer<Game>> observers = new ArrayList<>();


    @Override
    public void addObserver(Observer<Game> observer) {
        observers.add(observer);
    }

    @Override
    public void updateObservers() {
        observers.forEach(z -> z.update(this));
    }
}
