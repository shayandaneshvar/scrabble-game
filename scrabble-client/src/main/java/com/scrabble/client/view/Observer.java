package com.scrabble.client.view;

import com.scrabble.client.model.Observable;

public interface Observer<T extends Observable<T>> {
    void update(T t);
}
