package com.scrabble.client.model;

import com.scrabble.client.view.Observer;

import java.util.ArrayList;
import java.util.List;

public class NetworkEnabledGame extends Game<NetworkEnabledGame> {
    private List<Observer<NetworkEnabledGame>> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer<NetworkEnabledGame> observer) {
        observers.add(observer);
    }

    @Override
    public void updateObservers() {
        observers.forEach(z -> z.update(this));
    }
}
