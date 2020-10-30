package com.scrabble.client.model;

import com.scrabble.client.view.Observer;

public interface Observable {

    void addObserver(Observer observer);

    void updateObservers();

    default void removeObserver(Observer observer) {
        throw new UnsupportedOperationException();
    }

    default void removeObservers() {
        throw new UnsupportedOperationException();
    }
}
