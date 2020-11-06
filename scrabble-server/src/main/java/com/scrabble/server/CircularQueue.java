package com.scrabble.server;

import java.util.Arrays;

public class CircularQueue<T> {
    private Object[] objects;
    private int pointer = 0;
    private int elementsAvailable = 0;
    private boolean init = false;

    public CircularQueue(int size) {
        objects = new Object[size];
    }

    public void push(T t) {
        if (elementsAvailable < objects.length) {
            objects[pointer++] = t;
            elementsAvailable++;
            return;
        }
        throw new QueueException("Queue size is " + objects.length + " , Queue is Full!");
    }

    public void init() {
        if (elementsAvailable == objects.length) {
            init = true;
            return;
        }
        throw new QueueException("Queue Has Empty Slots Available");
    }

    public T pop() {
        if (init) {
            T t = (T) (objects[pointer % objects.length]);
            pointer++;
            return t;
        }
        throw new QueueException("Circular Queue Should be Initialized first!");
    }


    public static class QueueException extends RuntimeException {
        public QueueException() {
        }

        public QueueException(String message) {
            super(message);
        }

        public QueueException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(objects);
    }

}
