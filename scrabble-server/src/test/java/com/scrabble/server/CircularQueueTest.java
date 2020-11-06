package com.scrabble.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircularQueueTest {
    CircularQueue<String> queue;

    @BeforeEach
    public void prepareQueue() {
        queue = new CircularQueue<>(3);
        queue.push("1");
        queue.push("2");
        queue.push("3");
    }

    @Test
    public void testQueuePushThrows() {
        Assertions.assertThrows(CircularQueue.QueueException.class, () -> queue.push("4"));
    }

    @Test
    public void testQueueInit() {
        Assertions.assertThrows(CircularQueue.QueueException.class, queue::pop);
    }

    @Test
    public void testQueuePop() {
        queue.init();
        String result;
        for (int i = 0; i < 30; i++) {
            result = queue.pop();
            Assertions.assertEquals(result, "1");
            result = queue.pop();
            Assertions.assertEquals(result, "2");
            result = queue.pop();
            Assertions.assertEquals(result, "3");
        }
    }

}
