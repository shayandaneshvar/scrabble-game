package com.scrabble.client.controller;

public interface Command<T> {
    void execute(T t);
}
