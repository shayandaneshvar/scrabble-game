package com.scrabble.client.model;

import com.scrabble.client.view.Observer;

public interface Observable<T extends Observable<T>> {

    void addObserver(Observer<T> observer);

    void updateObservers();

    default void removeObserver(Observer<T> observer) {
        throw new UnsupportedOperationException();
    }

    default void removeObservers() {
        throw new UnsupportedOperationException();
    }
}
